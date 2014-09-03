'use strict';

angular.module('tttService', ['ttt.resources'])
    .service('TTTService', function (TTTResourceFactory) {
        return {
            newGame: function (firstPlayer) {
                return TTTResourceFactory.newGame.get({firstPlayer: firstPlayer})
            },
            play: function (nextPosition, board) {
                return TTTResourceFactory.play.get({nextPosition: nextPosition, board: board})

            }
        }

    });