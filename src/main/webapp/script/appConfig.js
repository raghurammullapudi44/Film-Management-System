var app = angular.module('myApplication', ['ngRoute']);

app.config(function($routeProvider) {
	  $routeProvider

	  .when('/AddNewFilm', {
	    templateUrl : 'AddNewFilm.html',
	  })
	  
	  .when('/RemoveFilm', {
	    templateUrl : 'RemoveFilm.html',
	  })

	  .when('/ModifyFilm', {
	    templateUrl : 'ModifyFilm.html',
	  })
	  
	  .when('/AddNewActor', {
	    templateUrl : 'AddNewActor.html',
	  })
	  
	  .when('/ModifyActor', {
	    templateUrl : 'ModifyActor.html',
	  })
	  
	  .when('/RemoveActor', {
	    templateUrl : 'RemoveActor.html',
	  })
	  
	  .when('/SearchActor', {
	    templateUrl : 'SearchActor.html',
	  })
	  
	  .when('/statusMsg',{
		  templateUrl : 'statusMsg.html',
	  })
	  .otherwise({redirectTo: '/'});
	});


