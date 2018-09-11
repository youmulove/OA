function bigImages(){
	'use strict';

	  var console = window.console || { log: function () {} };
	  var $minimages = $('.docs-pictures');
	  var handler = function (e) {
	        console.log(e.type);
	      };
	     
	  var options = {
	     
	        url: 'data-original',
	        build: handler,
	        built: handler,
	        show: handler,
	        shown: handler,
	        hide: handler,
	        hidden: handler
	      };

	  $minimages.on({
	    'build.viewer': handler,
	    'built.viewer': handler,
	    'show.viewer': handler,
	    'shown.viewer': handler,
	    'hide.viewer': handler,
	    'hidden.viewer': handler
	  }).viewer(options);
	
}