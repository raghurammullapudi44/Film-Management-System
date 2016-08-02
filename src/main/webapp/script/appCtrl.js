app.controller('allActors',function($scope,$http){
	
	var url='http://localhost:8018/angularjs_webapp/ViewAllActors';
	
	$http.get(url)
		.success(function(response){
			$scope.actors=response;
		});
	
	});

app.controller('allFilms',function($scope,$http){
	
	var url='http://localhost:8018/angularjs_webapp/ViewALLFilms';
	
	$http.get(url)
		.success(function(response){
			$scope.films=response;
		});
	
	});

app.controller('allLanguages',function($scope,$http){
	
	var url='http://localhost:8018/angularjs_webapp/ViewAllLanguages';
	
	$http.get(url)
		.success(function(response){
			$scope.languages=response;
		});
	});

app.controller('allCategories',function($scope,$http){
	
	var url='http://localhost:8018/angularjs_webapp/ViewAllCategories';
	
	$http.get(url)
		.success(function(response){
			$scope.categories=response;
		});
	});


app.controller('addNewFilm',function($scope,$http){
	
$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded; charset=utf-8";
	
	$scope.sendPost = function() {
		$http({
			url : 'http://localhost:8018/angularjs_webapp/AddNewFilm',
			method : "POST",
			data : {
				'title' : $scope.title,
				'description':$scope.description,
				'rating' : $scope.rating,
				'rentalduration':$scope.rentalduration,
				'rentalRate' : $scope.rentalRate,
				'replacementCost':$scope.description,
				'specialFeatures':$scope.specialFeatures,
				'releaseDate' : $scope.releaseDate,
				'languageName':$scope.languageName,
				'categoryName' : $scope.categoryName,
				'actors': $scope.filmActors
			}
		}).then(function(response) {
			alert($scope.languageName)
			$scope.message = response.data;
		}, function(response) {
			//fail case
			console.log(response);
			$scope.message = response;
		});
	};
	});

app.controller('statusMsg',function($scope,$http){
		$scope.message="Operation Successful";
	});


		app.controller('myCtrl', function($scope,$window,$location) 
		{
			$scope.$on('$locationChangeStart', function() {
		        var d = $scope.action.buttonClicked;

		        // if there isn't any clicked button
		        if (!Object.keys(d).map(function(k) { return d[k]; }).some(angular.identity))
		        {
		            // redirect to main page
		            $location.url('/');
		        }
		    });
			
			$scope.showButtons = function() 
			{
				$scope.action.buttonClicked.addFilm = false
				$scope.action.buttonClicked.modifyFilm = false
				$scope.action.buttonClicked.searchFilm = false
				$scope.action.buttonClicked.removeFilm = false
				$scope.action.buttonClicked.getAllFilms = false
				$scope.action.buttonClicked.addActor = false
				$scope.action.buttonClicked.modifyActor = false
				$scope.action.buttonClicked.searchActor = false
				$scope.action.buttonClicked.removeActor = false
				$scope.action.buttonClicked.getAllActors = false
			}
			
			$scope.action = 
			{
					buttonClicked: {},
					addFilm: function()
					{
						$scope.action.buttonClicked.addFilm = true
					},
					modifyFilm: function()
					{
						$scope.action.buttonClicked.modifyFilm = true
					},
					searchFilm: function()
					{
						$scope.action.buttonClicked.searchFilm = true
					},
					removeFilm: function()
					{
						$scope.action.buttonClicked.removeFilm = true
					},
					getAllFilms: function()
					{
						$scope.action.buttonClicked.getAllFilms = true
					},
					addActor: function()
					{
						$scope.action.buttonClicked.addActor = true
					},
					modifyActor: function()
					{
						$scope.action.buttonClicked.modifyActor = true
					},
					searchActor: function()
					{
						$scope.action.buttonClicked.searchActor = true
					},
					removeActor: function()
					{
						$scope.action.buttonClicked.removeActor = true
					},
					getAllActors: function()
					{
						$scope.action.buttonClicked.getAllActors = true
					}
			}
		});