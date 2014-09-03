angular.module('ttt.resources', ['ngResource'])
    .factory('TTTResourceFactory', function ($resource) {
        return {
            newGame: $resource(sprintf('%s/api/new_game',
                    CONFIG.serverURI),
                {
                }),
            play: $resource(sprintf('%s/api/nextMove',
                CONFIG.serverURI))
        };
    });
