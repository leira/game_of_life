(ns game-of-life.db
  (:require [game-of-life.game :as game]))

(def world [80 80])

(def default-db
  {:cells #{      [0 1]
                        [1 2]
            [2 0] [2 1] [2 2]}})
