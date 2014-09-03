angular.module('ttt.resources', ['ngResource'])
    .factory('TTTResourceFactory', function ($resource) {
        return {
            newGame: $resource(sprintf('%s/api/ttt/new_game',
                    CONFIG.serverURI),
                {
                }),
            play: $resource(sprintf('%s/api/ttt/nextMove',
                CONFIG.serverURI))
        };
    });
