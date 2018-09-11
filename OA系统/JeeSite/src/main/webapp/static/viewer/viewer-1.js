/****************520行不同*/
/*!
 * Viewer v0.1.0
 * https://github.com/fengyuanchen/viewer
 *
 * Copyright (c) 2015 Fengyuan Chen
 * Released under the MIT license
 *
 * Date: 2015-09-02T09:08:17.666Z
 */

(function (factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as anonymous module.
    define('viewer', ['jquery'], factory);
  } else if (typeof exports === 'object') {
    // Node / CommonJS
    factory(require('jquery'));
  } else {
    // Browser globals.
    factory(jQuery);
  }
})(function ($min) {

  'use strict';

  var $minwindow = $min(window);
  var $mindocument = $min(document);

  // Constants
  var NAMESPACE = 'viewer';
  var ELEMENT_VIEWER = document.createElement(NAMESPACE);

  // Classes
  var CLASS_TOGGLE = 'viewer-toggle';
  var CLASS_FIXED = 'viewer-fixed';
  var CLASS_OPEN = 'viewer-open';
  var CLASS_SHOW = 'viewer-show';
  var CLASS_HIDE = 'viewer-hide';
  var CLASS_FADE = 'viewer-fade';
  var CLASS_IN = 'viewer-in';
  var CLASS_MOVE = 'viewer-move';
  var CLASS_ACTIVE = 'viewer-active';
  var CLASS_INVISIBLE = 'viewer-invisible';
  var CLASS_TRANSITION = 'viewer-transition';
  var CLASS_FULLSCREEN = 'viewer-fullscreen';
  var CLASS_FULLSCREEN_EXIT = 'viewer-fullscreen-exit';
  var CLASS_CLOSE = 'viewer-close';

  // Selectors
  var SELECTOR_IMG = 'img';

  // Events
  var EVENT_MOUSEDOWN = 'mousedown touchstart pointerdown MSPointerDown';
  var EVENT_MOUSEMOVE = 'mousemove touchmove pointermove MSPointerMove';
  var EVENT_MOUSEUP = 'mouseup touchend touchcancel pointerup pointercancel MSPointerUp MSPointerCancel';
  var EVENT_WHEEL = 'wheel mousewheel DOMMouseScroll';
  var EVENT_TRANSITIONEND = 'transitionend';
  var EVENT_LOAD = 'load.' + NAMESPACE;
  var EVENT_KEYDOWN = 'keydown.' + NAMESPACE;
  var EVENT_CLICK = 'click.' + NAMESPACE;
  var EVENT_RESIZE = 'resize.' + NAMESPACE;
  var EVENT_BUILD = 'build.' + NAMESPACE;
  var EVENT_BUILT = 'built.' + NAMESPACE;
  var EVENT_SHOW = 'show.' + NAMESPACE;
  var EVENT_SHOWN = 'shown.' + NAMESPACE;
  var EVENT_HIDE = 'hide.' + NAMESPACE;
  var EVENT_HIDDEN = 'hidden.' + NAMESPACE;
  var EVENT_VIEW = 'view.' + NAMESPACE;
  var EVENT_VIEWED = 'viewed.' + NAMESPACE;

  // Supports
  var SUPPORT_TRANSITION = typeof ELEMENT_VIEWER.style.transition !== 'undefined';

  // Others
  var round = Math.round;
  var sqrt = Math.sqrt;
  var abs = Math.abs;
  var min = Math.min;
  var max = Math.max;
  var num = parseFloat;

  // Prototype
  var prototype = {};

  function isString(s) {
    return typeof s === 'string';
  }

  function isNumber(n) {
    return typeof n === 'number' && !isNaN(n);
  }

  function isUndefined(u) {
    return typeof u === 'undefined';
  }

  function toArray(obj, offset) {
    var args = [];

    if (isNumber(offset)) { // It's necessary for IE8
      args.push(offset);
    }

    return args.slice.apply(obj, args);
  }

  // Custom proxy to avoid jQuery's guid
  function proxy(fn, context) {
    var args = toArray(arguments, 2);

    return function () {
      return fn.apply(context, args.concat(toArray(arguments)));
    };
  }

  function getTransform(options) {
    var transforms = [];
    var rotate = options.rotate;
    var scaleX = options.scaleX;
    var scaleY = options.scaleY;

    if (isNumber(rotate)) {
      transforms.push('rotate(' + rotate + 'deg)');
    }

    if (isNumber(scaleX) && isNumber(scaleY)) {
      transforms.push('scale(' + scaleX + ',' + scaleY + ')');
    }

    return transforms.length ? transforms.join(' ') : 'none';
  }

  // e.g.: http://domain.com/path/to/picture.jpg?size=1280×960 -> picture.jpg
  function getImageName(url) {
    return isString(url) ? url.replace(/^.*\//, '').replace(/[\?&#].*$min/, '') : '';
  }

  function getImageSize(image, callback) {
    var newImage;

    // Modern browsers
    if (image.naturalWidth) {
      return callback(image.naturalWidth, image.naturalHeight);
    }

    // IE8: Don't use `new Image()` here
    newImage = document.createElement('img');

    newImage.onload = function () {
      callback(this.width, this.height);
    };

    newImage.src = image.src;
  }

  function Viewer(element, options) {
    this.$minelement = $min(element);
    this.options = $min.extend({}, Viewer.DEFAULTS, $min.isPlainObject(options) && options);
    this.isImg = false;
    this.isBuilt = false;
    this.isShown = false;
    this.isViewed = false;
    this.isFulled = false;
    this.isPlayed = false;
    this.playing = false;
    this.fading = false;
    this.transitioning = false;
    this.action = false;
    this.target = false;
    this.index = 0;
    this.length = 0;
    this.init();
  }

  $min.extend(prototype, {
    init: function () {
      var options = this.options;
      var $minthis = this.$minelement;
      var isImg = $minthis.is(SELECTOR_IMG);
      var $minimages = isImg ? $minthis : $minthis.find(SELECTOR_IMG);
      var length = $minimages.length;
      var ready = $min.proxy(this.ready, this);

      if (!length) {
        return;
      }

      if ($min.isFunction(options.build)) {
        $minthis.one(EVENT_BUILD, options.build);
      }

      if (this.trigger(EVENT_BUILD).isDefaultPrevented()) {
        return;
      }

      // Override `transiton` option if it is not supported
      if (!SUPPORT_TRANSITION) {
        options.transition = false;
      }

      this.isImg = isImg;
      this.length = length;
      this.count = 0;
      this.$minimages = $minimages;
      this.$minbody = $min('body');

      if (options.inline) {
        $minthis.one(EVENT_BUILT, $min.proxy(function () {
          this.view();
        }, this));

        $minimages.each(function () {
          if (this.complete) {
            ready();
          } else {
            $min(this).one(EVENT_LOAD, ready);
          }
        });
      } else {
        $minimages.addClass(CLASS_TOGGLE);
        $minthis.on(EVENT_CLICK, $min.proxy(this.start, this));
      }
    },

    ready: function () {
      this.count++;

      if (this.count === this.length) {
        this.build();
      }
    }
  });

  $min.extend(prototype, {
    build: function () {
      var options = this.options;
      var $minthis = this.$minelement;
      var $minparent;
      var $minviewer;
      var $minbutton;
      var $mintoolbar;

      if (this.isBuilt) {
        return;
      }

      if (!$minparent || !$minparent.length) {
        $minparent = $minthis.parent();
      }

      this.$minparent = $minparent;
      this.$minviewer = $minviewer = $min(Viewer.TEMPLATE);
      this.$mincanvas = $minviewer.find('.viewer-canvas');
      this.$minfooter = $minviewer.find('.viewer-footer');
      this.$mintitle = $minviewer.find('.viewer-title').toggleClass(CLASS_HIDE, !options.title);
      this.$mintoolbar = $mintoolbar = $minviewer.find('.viewer-toolbar').toggleClass(CLASS_HIDE, !options.toolbar);
      this.$minnavbar = $minviewer.find('.viewer-navbar').toggleClass(CLASS_HIDE, !options.navbar);
      this.$minbutton = $minbutton = $minviewer.find('.viewer-button').toggleClass(CLASS_HIDE, !options.button);
      this.$mintooltip = $minviewer.find('.viewer-tooltip');
      this.$minplayer = $minviewer.find('.viewer-player');
      this.$minlist = $minviewer.find('.viewer-list');

      $mintoolbar.find('li[class*=zoom]').toggleClass(CLASS_INVISIBLE, !options.zoomable);
      $mintoolbar.find('li[class*=flip]').toggleClass(CLASS_INVISIBLE, !options.scalable);

      if (!options.rotatable) {
        $mintoolbar.find('li[class*=rotate]').addClass(CLASS_INVISIBLE).appendTo($mintoolbar);
      }

      if (options.inline) {
        $minbutton.addClass(CLASS_FULLSCREEN);
        $minviewer.css('z-index', options.zIndexInline);

        if ($minparent.css('position') === 'static') {
          $minparent.css('position', 'relative');
        }
      } else {
        $minbutton.addClass(CLASS_CLOSE);
        $minviewer.
          css('z-index', options.zIndex).
          addClass([CLASS_FIXED, CLASS_FADE, CLASS_HIDE].join(' '));
      }

      $minthis.after($minviewer);

      if (options.inline) {
        this.render();
        this.bind();
        this.isShown = true;
      }

      this.isBuilt = true;

      if ($min.isFunction(options.built)) {
        $minthis.one(EVENT_BUILT, options.built);
      }

      this.trigger(EVENT_BUILT);
    },

    unbuild: function () {
      var options = this.options;
      var $minthis = this.$minelement;

      if (!this.isBuilt) {
        return;
      }

      if (options.inline && !options.container) {
        $minthis.removeClass(CLASS_HIDE);
      }

      this.$minviewer.remove();
    }
  });

  $min.extend(prototype, {
    bind: function () {
      this.$minviewer.
        on(EVENT_CLICK, $min.proxy(this.click, this)).
        on(EVENT_WHEEL, $min.proxy(this.wheel, this));

      this.$mincanvas.on(EVENT_MOUSEDOWN, $min.proxy(this.mousedown, this));

      $mindocument.
        on(EVENT_MOUSEMOVE, (this._mousemove = proxy(this.mousemove, this))).
        on(EVENT_MOUSEUP, (this._mouseup = proxy(this.mouseup, this))).
        on(EVENT_KEYDOWN, (this._keydown = proxy(this.keydown, this)));

      $minwindow.on(EVENT_RESIZE, (this._resize = proxy(this.resize, this)));
    },

    unbind: function () {
      this.$minviewer.
        off(EVENT_CLICK, this.click).
        off(EVENT_WHEEL, this.wheel);

      this.$mincanvas.off(EVENT_MOUSEDOWN, this.mousedown);

      $mindocument.
        off(EVENT_MOUSEMOVE, this._mousemove).
        off(EVENT_MOUSEUP, this._mouseup).
        off(EVENT_KEYDOWN, this._keydown);

      $minwindow.off(EVENT_RESIZE, this._resize);
    }
  });

  $min.extend(prototype, {
    render: function () {
      this.initContainer();
      this.initViewer();
      this.initList();
      this.renderViewer();
    },

    initContainer: function () {
      this.container = {
        width: $minwindow.innerWidth(),
        height: $minwindow.innerHeight()
      };
    },

    initViewer: function () {
      var options = this.options;
      var $minparent = this.$minparent;
      var viewer;

      if (options.inline) {
        this.parent = viewer = {
          width: max($minparent.width(), options.minWidth),
          height: max($minparent.height(), options.minHeight)
        };
      }

      if (this.isFulled || !viewer) {
        viewer = this.container;
      }

      this.viewer = $min.extend({}, viewer);
    },

    renderViewer: function () {
      if (this.options.inline && !this.isFulled) {
        this.$minviewer.css(this.viewer);
      }
    },

    initList: function () {
      var options = this.options;
      var $minthis = this.$minelement;
      var $minlist = this.$minlist;
      var list = [];

      this.$minimages.each(function (i) {
        var src = this.src;
        var alt = this.alt || getImageName(src);
        var url = options.url;

        if (!src) {
          return;
        }

        if (isString(url)) {
          url = this.getAttribute(url);
        } else if ($min.isFunction(url)) {
          url = url.call(this, this);
        }

        list.push(
          '<li>' +
            '<img' +
              ' src="' + src + '"' +
              ' data-action="view"' +
              ' data-index="' +  i + '"' +
              ' data-original-url="' +  (url || src) + '"' +
              ' alt="' +  alt + '"' +
            '>' +
          '</li>'
        );
      });

      $minlist.html(list.join('')).find(SELECTOR_IMG).one(EVENT_LOAD, {
        filled: true
      }, $min.proxy(this.loadImage, this));

      this.$minitems = $minlist.children();

      if (options.transition) {
        $minthis.one(EVENT_VIEWED, function () {
          $minlist.addClass(CLASS_TRANSITION);
        });
      }
    },

    renderList: function (index) {
      var i = index || this.index;
      var width = this.$minitems.eq(i).width();
      var outerWidth = width + 1; // 1 pixel of `margin-left` width

      // Place the active item in the center of the screen
      this.$minlist.css({
        width: outerWidth * this.length,
        marginLeft: (this.viewer.width - width) / 2 - outerWidth * i
      });
    },

    resetList: function () {
      this.$minlist.empty().removeClass(CLASS_TRANSITION).css('margin-left', 0);
    },

    initImage: function (callback) {
      var options = this.options;
      var $minimage = this.$minimage;
      var viewer = this.viewer;
      var footerHeight = this.$minfooter.height();
      var viewerWidth = viewer.width;
      var viewerHeight = max(viewer.height - footerHeight, footerHeight);
      var oldImage = this.image || {};

      getImageSize($minimage[0], $min.proxy(function (naturalWidth, naturalHeight) {
        var aspectRatio = naturalWidth / naturalHeight;
        var width = viewerWidth;
        var height = viewerHeight;
        var initialImage;
        var image;

        if (viewerHeight * aspectRatio > viewerWidth) {
          height = viewerWidth / aspectRatio;
        } else {
          width = viewerHeight * aspectRatio;
        }

        width = min(width * 0.9, naturalWidth);
        height = min(height * 0.9, naturalHeight);

        image = {
          naturalWidth: naturalWidth,
          naturalHeight: naturalHeight,
          aspectRatio: aspectRatio,
          ratio: width / naturalWidth,
          width: width,
          height: height,
          left: (viewerWidth - width) / 2,
          top: (viewerHeight - height) / 2
        };

        initialImage = $min.extend({}, image);

        if (options.rotatable) {
          image.rotate = oldImage.rotate || 0;
          initialImage.rotate = 0;
        }

        if (options.scalable) {
          image.scaleX = oldImage.scaleX || 1;
          image.scaleY = oldImage.scaleY || 1;
          initialImage.scaleX = 1;
          initialImage.scaleY = 1;
        }

        this.image = image;
        this.initialImage = initialImage;

        if ($min.isFunction(callback)) {
          callback();
        }
      }, this));
    },

    renderImage: function (callback) {
      var image = this.image;
      var $minimage = this.$minimage;

      $minimage.css({
        width: image.width,
        height: image.height,
        marginLeft: image.left,
        marginTop: image.top-250,
        transform: getTransform(image)
      });

      if ($min.isFunction(callback)) {
        if (this.options.transition) {
          $minimage.one(EVENT_TRANSITIONEND, callback);
        } else {
          callback();
        }
      }
    },

    resetImage: function () {
      this.$minimage.remove();
      this.$minimage = null;
    }
  });

  $min.extend(prototype, {
    start: function (e) {
      var target = e.target;

      if ($min(target).hasClass(CLASS_TOGGLE)) {
        this.target = target;
        this.show();
      }
    },

    click: function (e) {
      var $mintarget = $min(e.target);
      var action = $mintarget.data('action');
      var image = this.image;

      switch (action) {
        case 'mix':
          if (this.isPlayed) {
            this.stop();
          } else {
            if (this.options.inline) {
              if (this.isFulled) {
                this.exit();
              } else {
                this.full();
              }
            } else {
              this.hide();
            }
          }

          break;

        case 'view':
          this.view($mintarget.data('index'));
          break;

        case 'zoom-in':
          this.zoom(0.1, true);
          break;

        case 'zoom-out':
          this.zoom(-0.1, true);
          break;

        case 'one-to-one':
          if (this.image.ratio === 1) {
            this.zoomTo(this.initialImage.ratio);
          } else {
            this.zoomTo(1);
          }

          break;

        case 'reset':
          this.reset();
          break;

        case 'prev':
          this.prev();
          break;

        case 'play':
          this.play();
          break;

        case 'next':
          this.next();
          break;

        case 'rotate-left':
          this.rotate(-90);
          break;

        case 'rotate-right':
          this.rotate(90);
          break;

        case 'flip-horizontal':
          this.scale(-image.scaleX || -1, image.scaleY || 1);
          break;

        case 'flip-vertical':
          this.scale(image.scaleX || 1, -image.scaleY || -1);
          break;

        default:
          if (this.isPlayed) {
            this.stop();
          }
      }
    },

    load: function () {
      this.initImage($min.proxy(function () {
        this.renderImage($min.proxy(function () {
          this.isViewed = true;
          this.trigger(EVENT_VIEWED);
        }, this));
      }, this));
    },

    loadImage: function (e) {
      var image = e.target;
      var $minimage = $min(image);
      var $minparent = $minimage.parent();
      var parentWidth = $minparent.width();
      var parentHeight = $minparent.height();
      var filled = e.data && e.data.filled;

      getImageSize(image, $min.proxy(function (naturalWidth, naturalHeight) {
        var aspectRatio = naturalWidth / naturalHeight;
        var width = parentWidth;
        var height = parentHeight;

        if (parentHeight * aspectRatio > parentWidth) {
          if (filled) {
            width = parentHeight * aspectRatio;
          } else {
            height = parentWidth / aspectRatio;
          }
        } else {
          if (filled) {
            height = parentWidth / aspectRatio;
          } else {
            width = parentHeight * aspectRatio;
          }
        }

        $minimage.css({
          width: width,
          height: height,
          marginLeft: (parentWidth - width) / 2,
          marginTop: (parentHeight - height) / 2
        });
      }, this));
    },

    resize: function () {
      this.initContainer();
      this.initViewer();
      this.renderViewer();
      this.renderList();
      this.initImage($min.proxy(function () {
        this.renderImage();
      }, this));

      if (this.isPlayed) {
        this.$minplayer.
          find(SELECTOR_IMG).
          one(EVENT_LOAD, $min.proxy(this.loadImage, this)).
          trigger(EVENT_LOAD);
      }
    },

    wheel: function (event) {
      var e = event.originalEvent;
      var ratio = num(this.options.zoomRatio) || 0.1;
      var delta = 1;

      if (!this.isViewed) {
        return;
      }

      event.preventDefault();

      if (e.deltaY) {
        delta = e.deltaY > 0 ? 1 : -1;
      } else if (e.wheelDelta) {
        delta = -e.wheelDelta / 120;
      } else if (e.detail) {
        delta = e.detail > 0 ? 1 : -1;
      }

      this.zoom(-delta * ratio, true);
    },

    keydown: function (e) {
      var options = this.options;
      var which = e.which;

      if (!this.isFulled || !options.keyboard) {
        return;
      }

      switch (which) {

        // (Key: Esc)
        case 27:
          if (this.isPlayed) {
            this.stop();
          } else {
            if (options.inline) {
              if (this.isFulled) {
                this.exit();
              }
            } else {
              this.hide();
            }
          }

          break;

        // View previous (Key: ←)
        case 37:
          this.prev();
          break;

        // Zoom in (Key: ↑)
        case 38:
          this.zoom(options.zoomRatio, true);
          break;

        // View next (Key: →)
        case 39:
          this.next();
          break;

        // Zoom out (Key: ↓)
        case 40:
          this.zoom(-options.zoomRatio, true);
          break;

        // Zoom out to initial size (Key: Ctrl + 0)
        case 48:
          // Go to next

        // Zoom in to natural size (Key: Ctrl + 1)
        case 49:
          if (e.ctrlKey || e.shiftKey) {
            e.preventDefault();

            if (this.image.ratio === 1) {
              this.zoomTo(this.initialImage.ratio);
            } else {
              this.zoomTo(1);
            }
          }

          break;

        // No default
      }
    },

    mousedown: function (event) {
      var options = this.options;
      var originalEvent = event.originalEvent;
      var touches = originalEvent && originalEvent.touches;
      var e = event;
      var action = options.movable ? 'move' : false;
      var touchesLength;

      if (!this.isViewed) {
        return;
      }

      if (touches) {
        touchesLength = touches.length;

        if (touchesLength > 1) {
          if (options.zoomable && touchesLength === 2) {
            e = touches[1];
            this.startX2 = e.pageX;
            this.startY2 = e.pageY;
            action = 'zoom';
          } else {
            return;
          }
        } else {
          if (this.isSwitchable()) {
            action = 'switch';
          }
        }

        e = touches[0];
      }

      if (action) {
        event.preventDefault();

        if (action === 'move' && options.transition) {
          this.$minimage.removeClass(CLASS_TRANSITION);
        }

        this.action = action;

        // IE8  has `event.pageX/Y`, but not `event.originalEvent.pageX/Y`
        // IE10 has `event.originalEvent.pageX/Y`, but not `event.pageX/Y`
        this.startX = e.pageX || originalEvent && originalEvent.pageX;
        this.startY = e.pageY || originalEvent && originalEvent.pageY;
      }
    },

    mousemove: function (event) {
      var options = this.options;
      var originalEvent = event.originalEvent;
      var touches = originalEvent && originalEvent.touches;
      var e = event;
      var touchesLength;

      if (!this.isViewed) {
        return;
      }

      if (touches) {
        touchesLength = touches.length;

        if (touchesLength > 1) {
          if (options.zoomable && touchesLength === 2) {
            e = touches[1];
            this.endX2 = e.pageX;
            this.endY2 = e.pageY;
          } else {
            return;
          }
        }

        e = touches[0];
      }

      if (this.action) {
        event.preventDefault();

        this.endX = e.pageX || originalEvent && originalEvent.pageX;
        this.endY = e.pageY || originalEvent && originalEvent.pageY;

        this.change();
      }
    },

    mouseup: function (event) {
      var action = this.action;

      if (action) {
        event.preventDefault();

        if (action === 'move' && this.options.transition) {
          this.$minimage.addClass(CLASS_TRANSITION);
        }

        this.action = false;
      }
    }
  });

  $min.extend(prototype, {

    // Show the viewer (only available in modal mode)
    show: function () {
      var options = this.options;
      var $minviewer;

      if (options.inline || this.transitioning) {
        return;
      }

      if (!this.isBuilt) {
        this.build();
      }

      if ($min.isFunction(options.show)) {
        this.$minelement.one(EVENT_SHOW, options.show);
      }

      if (this.trigger(EVENT_SHOW).isDefaultPrevented()) {
        return;
      }

      this.$minbody.addClass(CLASS_OPEN);
      $minviewer = this.$minviewer.removeClass(CLASS_HIDE);

      this.$minelement.one(EVENT_SHOWN, $min.proxy(function () {
        this.view((this.target ? this.$minimages.index(this.target) : 0) || this.index);
        this.target = false;
      }, this));

      if (options.transition) {
        this.transitioning = true;

        /* jshint expr:true */
        $minviewer.addClass(CLASS_TRANSITION).get(0).offsetWidth;
        $minviewer.one(EVENT_TRANSITIONEND, $min.proxy(this.shown, this)).addClass(CLASS_IN);
      } else {
        $minviewer.addClass(CLASS_IN);
        this.shown();
      }
    },

    // Hide the viewer (only available in modal mode)
    hide: function () {
      var options = this.options;
      var $minviewer = this.$minviewer;

      if (options.inline || this.transitioning || !this.isShown) {
        return;
      }

      if ($min.isFunction(options.hide)) {
        this.$minelement.one(EVENT_HIDE, options.hide);
      }

      if (this.trigger(EVENT_HIDE).isDefaultPrevented()) {
        return;
      }

      if (this.isViewed && options.transition) {
        this.transitioning = true;
        this.$minimage.one(EVENT_TRANSITIONEND, $min.proxy(function () {
          $minviewer.one(EVENT_TRANSITIONEND, $min.proxy(this.hidden, this)).removeClass(CLASS_IN);
        }, this));
        this.zoomTo(0, false, true);
      } else {
        $minviewer.removeClass(CLASS_IN);
        this.hidden();
      }
    },

    /**
     * View one of the images with image's index
     *
     * @param {Number} index
     */
    view: function (index) {
      var options = this.options;
      var viewer = this.viewer;
      var $mintitle = this.$mintitle;
      var $minimage;
      var $minitem;
      var $minimg;
      var url;
      var alt;

      index = Number(index) || 0;

      if (!this.isShown || this.isPlayed || index < 0 || index >= this.length ||
        this.isViewed && index === this.index) {
        return;
      }

      if (this.trigger(EVENT_VIEW).isDefaultPrevented()) {
        return;
      }

      $minitem = this.$minitems.eq(index);
      $minimg = $minitem.find(SELECTOR_IMG);
      url = $minimg.data('originalUrl');
      alt = $minimg.attr('alt');

      this.$minimage = $minimage = $min('<img src="' + url + '" alt="' + alt + '">');

      $minimage.
        toggleClass(CLASS_TRANSITION, options.transition).
        toggleClass(CLASS_MOVE, options.movable).
        css({
          width: 0,
          height: 0,
          marginLeft: viewer.width / 2,
          marginTop: viewer.height / 2
        });

      this.$minitems.eq(this.index).removeClass(CLASS_ACTIVE);
      $minitem.addClass(CLASS_ACTIVE);

      this.isViewed = false;
      this.index = index;
      this.image = null;
      $minimage.one(EVENT_LOAD, $min.proxy(this.load, this));
      this.$mincanvas.html($minimage);
      $mintitle.empty();

      // Center current item
      this.renderList();

      // Show title when viewed
      this.$minelement.one(EVENT_VIEWED, $min.proxy(function () {
        var image = this.image;
        var width = image.naturalWidth;
        var height = image.naturalHeight;

        $mintitle.html(alt + ' (' + width + ' &times; ' + height + ')');
      }, this));
    },

    // View the previous image
    prev: function () {
      this.view(max(this.index - 1, 0));
    },

    // View the next image
    next: function () {
      this.view(min(this.index + 1, this.length - 1));
    },

    /**
     * Move the image
     *
     * @param {Number} offsetX
     * @param {Number} offsetY (optional)
     */
    move: function (offsetX, offsetY) {
      var image = this.image;

      // If "offsetY" is not present, its default value is "offsetX"
      if (isUndefined(offsetY)) {
        offsetY = offsetX;
      }

      offsetX = num(offsetX);
      offsetY = num(offsetY);

      if (this.isShown && !this.isPlayed && this.options.movable) {
        image.left += isNumber(offsetX) ? offsetX : 0;
        image.top += isNumber(offsetY) ? offsetY : 0;
        this.renderImage();
      }
    },

    /**
     * Zoom the image
     *
     * @param {Number} ratio
     * @param {Boolean} hasTooltip (optional)
     */
    zoom: function (ratio, hasTooltip) {
      var options = this.options;
      var minZoomRatio = max(0.01, options.minZoomRatio);
      var maxZoomRatio = min(100, options.maxZoomRatio);
      var image = this.image;
      var width;
      var height;

      ratio = num(ratio);

      if (isNumber(ratio) && this.isShown && !this.isPlayed && options.zoomable) {
        if (ratio < 0) {
          ratio =  1 / (1 - ratio);
        } else {
          ratio = 1 + ratio;
        }

        width = image.width * ratio;
        height = image.height * ratio;
        ratio = width / image.naturalWidth;
        ratio = min(max(ratio, minZoomRatio), maxZoomRatio);

        if (ratio > 0.95 && ratio < 1.05) {
          ratio = 1;
          width = image.naturalWidth;
          height = image.naturalHeight;
        }

        image.left -= (width - image.width) / 2;
        image.top -= (height - image.height) / 2;
        image.width = width;
        image.height = height;
        image.ratio = ratio;
        this.renderImage();

        if (hasTooltip) {
          this.tooltip();
        }
      }
    },

    /**
     * Zoom the image to a special ratio
     *
     * @param {Number} ratio
     * @param {Boolean} hasTooltip (optional)
     * @param {Boolean} _zoomable (private)
     */
    zoomTo: function (ratio, hasTooltip, _zoomable) {
      var image = this.image;
      var width;
      var height;

      ratio = max(ratio, 0);

      if (isNumber(ratio) && this.isShown && !this.isPlayed && (_zoomable || this.options.zoomable)) {
        width = image.naturalWidth * ratio;
        height = image.naturalHeight * ratio;
        image.left -= (width - image.width) / 2;
        image.top -= (height - image.height) / 2;
        image.width = width;
        image.height = height;
        image.ratio = ratio;
        this.renderImage();

        if (hasTooltip) {
          this.tooltip();
        }
      }
    },

    /**
     * Rotate the image
     * https://developer.mozilla.org/en-US/docs/Web/CSS/transform-function#rotate()
     *
     * @param {Number} degrees
     */
    rotate: function (degrees) {
      var image = this.image;

      degrees = num(degrees);

      if (isNumber(degrees) && this.isShown && !this.isPlayed && this.options.rotatable) {
        image.rotate = ((image.rotate || 0) + degrees);
        this.renderImage();
      }
    },

    /**
     * Rotate the image to a special angle
     *
     * @param {Number} degrees
     */
    rotateTo: function (degrees) {
      var image = this.image;

      degrees = num(degrees);

      if (isNumber(degrees) && this.isShown && !this.isPlayed && this.options.rotatable) {
        image.rotate = degrees;
        this.renderImage();
      }
    },

    /**
     * Scale the image
     * https://developer.mozilla.org/en-US/docs/Web/CSS/transform-function#scale()
     *
     * @param {Number} scaleX
     * @param {Number} scaleY (optional)
     */
    scale: function (scaleX, scaleY) {
      var image = this.image;

      // If "scaleY" is not present, its default value is "scaleX"
      if (isUndefined(scaleY)) {
        scaleY = scaleX;
      }

      scaleX = num(scaleX);
      scaleY = num(scaleY);

      if (this.isShown && !this.isPlayed && this.options.scalable) {
        image.scaleX = isNumber(scaleX) ? scaleX : 1;
        image.scaleY = isNumber(scaleY) ? scaleY : 1;
        this.renderImage();
      }
    },

    /**
     * Scale the abscissa of the image
     *
     * @param {Number} scaleX
     */
    scaleX: function (scaleX) {
      this.scale(scaleX, this.image.scaleY);
    },

    /**
     * Scale the ordinate of the image
     *
     * @param {Number} scaleY
     */
    scaleY: function (scaleY) {
      this.scale(this.image.scaleX, scaleY);
    },

    // Play the images
    play: function () {
      var options = this.options;
      var $minplayer = this.$minplayer;
      var load = $min.proxy(this.loadImage, this);
      var list = [];
      var total = 0;
      var index = 0;
      var playing;

      if (!this.isShown || this.isPlayed) {
        return;
      }

      if (options.fullscreen) {
        this.fullscreen();
      }

      this.isPlayed = true;
      $minplayer.addClass(CLASS_SHOW);

      this.$minitems.each(function (i) {
        var $minthis = $min(this);
        var $minimg = $minthis.find(SELECTOR_IMG);
        var $minimage = $min('<img src="' + $minimg.data('originalUrl') + '" alt="' + $minimg.attr('alt') + '">');

        total++;

        $minimage.addClass(CLASS_FADE).toggleClass(CLASS_TRANSITION, options.transition);

        if ($minthis.hasClass(CLASS_ACTIVE)) {
          $minimage.addClass(CLASS_IN);
          index = i;
        }

        list.push($minimage);
        $minimage.one(EVENT_LOAD, {
          filled: false
        }, load);
        $minplayer.append($minimage);
      });

      if (isNumber(options.interval) && options.interval > 0) {
        playing = $min.proxy(function () {
          this.playing = setTimeout(function () {
            list[index].removeClass(CLASS_IN);
            index++;
            index = index < total ? index : 0;
            list[index].addClass(CLASS_IN);

            playing();
          }, options.interval);
        }, this);

        if (total > 1) {
          playing();
        }
      }
    },

    // Stop play
    stop: function () {
      if (!this.isPlayed) {
        return;
      }

      this.isPlayed = false;
      clearTimeout(this.playing);
      this.$minplayer.removeClass(CLASS_SHOW).empty();
    },

    // Enter modal mode (only available in inline mode)
    full: function () {
      var options = this.options;
      var $minimage = this.$minimage;
      var $minlist = this.$minlist;

      if (!this.isShown || this.isPlayed || this.isFulled || !options.inline) {
        return;
      }

      this.isFulled = true;
      this.$minbody.addClass(CLASS_OPEN);
      this.$minbutton.addClass(CLASS_FULLSCREEN_EXIT);

      if (options.transition) {
        $minimage.removeClass(CLASS_TRANSITION);
        $minlist.removeClass(CLASS_TRANSITION);
      }

      this.$minviewer.addClass(CLASS_FIXED).removeAttr('style').css('z-index', options.zIndex);
      this.initContainer();
      this.viewer = $min.extend({}, this.container);
      this.renderList();
      this.initImage($min.proxy(function () {
        this.renderImage(function () {
          if (options.transition) {
            setTimeout(function () {
              $minimage.addClass(CLASS_TRANSITION);
              $minlist.addClass(CLASS_TRANSITION);
            }, 0);
          }
        });
      }, this));
    },

    // Exit modal mode (only available in inline mode)
    exit: function () {
      var options = this.options;
      var $minimage = this.$minimage;
      var $minlist = this.$minlist;

      if (!this.isFulled) {
        return;
      }

      this.isFulled = false;
      this.$minbody.removeClass(CLASS_OPEN);
      this.$minbutton.removeClass(CLASS_FULLSCREEN_EXIT);

      if (options.transition) {
        $minimage.removeClass(CLASS_TRANSITION);
        $minlist.removeClass(CLASS_TRANSITION);
      }

      this.$minviewer.removeClass(CLASS_FIXED).css('z-index', options.zIndexInline);
      this.viewer = $min.extend({}, this.parent);
      this.renderViewer();
      this.renderList();
      this.initImage($min.proxy(function () {
        this.renderImage(function () {
          if (options.transition) {
            setTimeout(function () {
              $minimage.addClass(CLASS_TRANSITION);
              $minlist.addClass(CLASS_TRANSITION);
            }, 0);
          }
        });
      }, this));
    },

    // Show the current ratio of the image with percentage
    tooltip: function () {
      var options = this.options;
      var $mintooltip = this.$mintooltip;
      var image = this.image;
      var classes = [
            CLASS_SHOW,
            CLASS_FADE,
            CLASS_TRANSITION
          ].join(' ');

      if (!this.isShown || this.isPlayed || !options.tooltip) {
        return;
      }

      $mintooltip.text(round(image.ratio * 100) + '%');

      if (!this.fading) {
        if (options.transition) {

          /* jshint expr:true */
          $mintooltip.addClass(classes).get(0).offsetWidth;
          $mintooltip.addClass(CLASS_IN);
        } else {
          $mintooltip.addClass(CLASS_SHOW);
        }
      } else {
        clearTimeout(this.fading);
      }

      this.fading = setTimeout($min.proxy(function () {
        if (options.transition) {
          $mintooltip.one(EVENT_TRANSITIONEND, function () {
            $mintooltip.removeClass(classes);
          }).removeClass(CLASS_IN);
        } else {
          $mintooltip.removeClass(CLASS_SHOW);
        }

        this.fading = false;
      }, this), 1000);
    },

    // Toggle the image size between its natural size and initial size.
    toggle: function () {
      if (this.image.ratio === 1) {
        this.zoomTo(this.initialImage.ratio);
      } else {
        this.zoomTo(1);
      }
    },

    // Reset the image to its initial state.
    reset: function () {
      if (this.isShown && !this.isPlayed) {
        this.image = $min.extend({}, this.initialImage);
        this.renderImage();
      }
    },

    // Destroy the viewer
    destroy: function () {
      var $minthis = this.$minelement;

      if (this.options.inline) {
        this.unbind();
      } else {
        if (this.isShown) {
          this.unbind();
        }

        this.$minimages.removeClass(CLASS_TOGGLE);
        $minthis.off(EVENT_CLICK, this.start);
      }

      this.unbuild();
      $minthis.removeData(NAMESPACE);
    }
  });

  $min.extend(prototype, {

    // A shortcut for triggering custom events
    trigger: function (type, data) {
      var e = $min.Event(type, data);

      this.$minelement.trigger(e);

      return e;
    },

    shown: function () {
      var options = this.options;

      this.transitioning = false;
      this.isFulled = true;
      this.isShown = true;
      this.isVisible = true;
      this.render();
      this.bind();

      if ($min.isFunction(options.shown)) {
        this.$minelement.one(EVENT_SHOWN, options.shown);
      }

      this.trigger(EVENT_SHOWN);
    },

    hidden: function () {
      var options = this.options;

      this.transitioning = false;
      this.isViewed = false;
      this.isFulled = false;
      this.isShown = false;
      this.isVisible = false;
      this.unbind();
      this.$minbody.removeClass(CLASS_OPEN);
      this.$minviewer.addClass(CLASS_HIDE);
      this.resetList();
      this.resetImage();

      if ($min.isFunction(options.hidden)) {
        this.$minelement.one(EVENT_HIDDEN, options.hidden);
      }

      this.trigger(EVENT_HIDDEN);
    },

    fullscreen: function () {
      var documentElement = document.documentElement;

      if (this.isFulled && !document.fullscreenElement && !document.mozFullScreenElement &&
        !document.webkitFullscreenElement && !document.msFullscreenElement) {

        if (documentElement.requestFullscreen) {
          documentElement.requestFullscreen();
        } else if (documentElement.msRequestFullscreen) {
          documentElement.msRequestFullscreen();
        } else if (documentElement.mozRequestFullScreen) {
          documentElement.mozRequestFullScreen();
        } else if (documentElement.webkitRequestFullscreen) {
          documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
        }
      }
    },

    change: function () {
      var offsetX = this.endX - this.startX;
      var offsetY = this.endY - this.startY;

      switch (this.action) {

        // Move the current image
        case 'move':
          this.move(offsetX, offsetY);
          break;

        // Zoom the current image
        case 'zoom':
          this.zoom(function (x1, y1, x2, y2) {
            var z1 = sqrt(x1 * x1 + y1 * y1);
            var z2 = sqrt(x2 * x2 + y2 * y2);

            return (z2 - z1) / z1;
          }(
            abs(this.startX - this.startX2),
            abs(this.startY - this.startY2),
            abs(this.endX - this.endX2),
            abs(this.endY - this.endY2)
          ));

          this.startX2 = this.endX2;
          this.startY2 = this.endY2;
          break;

        case 'switch':
          this.action = 'switched';

          if (offsetX > 1) {
            this.prev();
          } else if (offsetX < -1) {
            this.next();
          }

          break;

        // No default
      }

      // Override
      this.startX = this.endX;
      this.startY = this.endY;
    },

    isSwitchable: function () {
      var image = this.image;
      var viewer = this.viewer;

      return (image.left >= 0 && image.top >= 0 && image.width <= viewer.width &&
        image.height <= viewer.height);
    }
  });

  $min.extend(Viewer.prototype, prototype);

  Viewer.DEFAULTS = {
    // Enable inline mode
    inline: false,

    // Show the button on the top-right of the viewer
    button: true,

    // Show the navbar
    navbar: true,

    // Show the title
    title: true,

    // Show the toolbar
    toolbar: true,

    // Show the tooltip with image ratio (percentage) when zoom in or zoom out
    tooltip: true,

    // Enable to move the image
    movable: true,

    // Enable to zoom the image
    zoomable: true,

    // Enable to rotate the image
    rotatable: true,

    // Enable to scale the image
    scalable: true,

    // Enable CSS3 Transition for some special elements
    transition: true,

    // Enable to request fullscreen when play
    fullscreen: true,

    // Enable keyboard support
    keyboard: true,

    // Define interval of each image when playing
    interval: 5000,

    // Min width of the viewer in inline mode
    minWidth: 200,

    // Min height of the viewer in inline mode
    minHeight: 100,

    // Define the ratio when zoom the image by wheeling mouse
    zoomRatio: 0.1,

    // Define the min ratio of the image when zoom out
    minZoomRatio: 0.01,

    // Define the max ratio of the image when zoom in
    maxZoomRatio: 100,

    // Define the CSS `z-index` value of viewer in modal mode.
    zIndex: 2015,

    // Define the CSS `z-index` value of viewer in inline mode.
    zIndexInline: 0,

    // Define where to get the original image URL for viewing
    // Type: String (an image attribute) or Function (should return an image URL)
    url: 'src',

    // Event shortcuts
    build: null,
    built: null,
    show: null,
    shown: null,
    hide: null,
    hidden: null
  };

  Viewer.TEMPLATE = (
    '<div class="viewer-container">' +
      '<div class="viewer-canvas"></div>' +
      '<div class="viewer-footer">' +
        '<div class="viewer-title"></div>' +
        '<ul class="viewer-toolbar">' +
          '<li class="viewer-zoom-in" data-action="zoom-in"></li>' +
          '<li class="viewer-zoom-out" data-action="zoom-out"></li>' +
          '<li class="viewer-one-to-one" data-action="one-to-one"></li>' +
          '<li class="viewer-reset" data-action="reset"></li>' +
          '<li class="viewer-prev" data-action="prev"></li>' +
          '<li class="viewer-play" data-action="play"></li>' +
          '<li class="viewer-next" data-action="next"></li>' +
          '<li class="viewer-rotate-left" data-action="rotate-left"></li>' +
          '<li class="viewer-rotate-right" data-action="rotate-right"></li>' +
          '<li class="viewer-flip-horizontal" data-action="flip-horizontal"></li>' +
          '<li class="viewer-flip-vertical" data-action="flip-vertical"></li>' +
        '</ul>' +
        '<div class="viewer-navbar">' +
          '<ul class="viewer-list"></ul>' +
        '</div>' +
      '</div>' +
      '<div class="viewer-tooltip"></div>' +
      '<div class="viewer-button" data-action="mix"></div>' +
      '<div class="viewer-player"></div>' +
    '</div>'
  );

  // Save the other viewer
  Viewer.other = $min.fn.viewer;

  // Register as jQuery plugin
  $min.fn.viewer = function (options) {
    var args = toArray(arguments, 1);
    var result;

    this.each(function () {
      var $minthis = $min(this);
      var data = $minthis.data(NAMESPACE);
      var fn;

      if (!data) {
        if (/destroy|hide|exit|stop|reset/.test(options)) {
          return;
        }

        $minthis.data(NAMESPACE, (data = new Viewer(this, options)));
      }

      if (isString(options) && $min.isFunction(fn = data[options])) {
        result = fn.apply(data, args);
      }
    });

    return isUndefined(result) ? this : result;
  };

  $min.fn.viewer.Constructor = Viewer;
  $min.fn.viewer.setDefaults = Viewer.setDefaults;

  // No conflict
  $min.fn.viewer.noConflict = function () {
    $min.fn.viewer = Viewer.other;
    return this;
  };

});
