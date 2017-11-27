(ns game-of-life.db
  (:require [game-of-life.game :as game]))

(def default-db
  {:world (game/expand [[0 1 0]
                        [0 0 1]
                        [1 1 1]]
                       [80 80])})
;  {:world (for [x (range 20)]
;            (for [y (range 20)] (if (= x y) 1 0) ))})
