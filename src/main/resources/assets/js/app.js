'use strict';

var COMPUTER = "COMPUTER";
var OPPONENT = "OPPONENT";

angular.module('tttApp', ['tttService'])
    .controller('tttController', function ($scope, TTTService) {


        $scope.nextPlayer = COMPUTER;

        function changeNextPlayer() {
            if ($scope.nextPlayer == COMPUTER) {
                $scope.nextPlayer = OPPONENT
            } else {
                $scope.nextPlayer = COMPUTER
            }
        }

        function assignMovesToScopeVariables(data) {
            $scope.GameProgress = data;
            $scope.moves = $scope.GameProgress.board.moves;

            $scope.TOP_LEFT = $scope.moves[ 'TOP_LEFT'];
            $scope.TOP_CENTRE = $scope.moves['TOP_CENTRE'];
            $scope.TOP_RIGHT = $scope.moves['TOP_RIGHT'];
            $scope.MIDDLE_LEFT = $scope.moves['MIDDLE_LEFT'];
            $scope.CENTRE = $scope.moves['CENTRE'];
            $scope.MIDDLE_RIGHT = $scope.moves['MIDDLE_RIGHT'];
            $scope.BOTTOM_LEFT = $scope.moves['BOTTOM_LEFT'];
            $scope.BOTTOM_CENTRE = $scope.moves['BOTTOM_CENTRE'];
            $scope.BOTTOM_RIGHT = $scope.moves['BOTTOM_RIGHT'];
        }

        $scope.init = function (){
            changeNextPlayer();

            TTTService.newGame($scope.nextPlayer)
                .$promise
                .then(function (data) {
                    $scope.GameOver = false;
                    assignMovesToScopeVariables(data);
                })
        };

        $scope.init();

        $scope.update = function (position) {

            if (!$scope.moves[position] != 'EMPTY' && $scope.GameProgress.currentGameState == 'IN_PROGRESS') {
                TTTService.play(position, $scope.GameProgress.board)
                    .$promise
                    .then(function (data) {
                        $scope.GameProgress = data;
                        assignMovesToScopeVariables(data);

                        if($scope.GameProgress.currentGameState != 'IN_PROGRESS'){
                            $scope.GameOver = true;
                        }
                    })
            }
        };

        $scope.render = function (seed) {
            if (seed == "EMPTY") {
                return "";
            } else if (seed == "COMPUTER") {
                return "X";
            } else {
                return "O";
            }
        };

        $scope.renderResult = function(){
            var currentGameState = $scope.GameProgress.currentGameState;
            if(currentGameState=='COMPUTER_WINS'){
                return "Computer has won";
            }else if (currentGameState=='COMPUTER_LOSES'){
                return "Computer has lost";
            }else if(currentGameState=='DRAW'){
                return "Draw";
            }
        }
    }
)
;
