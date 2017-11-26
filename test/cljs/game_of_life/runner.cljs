(ns game-of-life.runner
    (:require [doo.runner :refer-macros [doo-tests doo-all-tests]]
              [game-of-life.core-test]
              [game-of-life.game-test]))

(doo-all-tests)
