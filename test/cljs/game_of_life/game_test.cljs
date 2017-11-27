(ns game-of-life.game-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [game-of-life.game :refer [neighbors step]]))

(deftest neighbors-test
  (testing "top left"
    (is (= (set (neighbors [3 4] [0 0]))
           #{      [0 1]
             [1 0] [1 1]})))
  (testing "right side"
    (is (= (set (neighbors [3 4] [1 3]))
           #{[0 2] [0 3]
             [1 2]
             [2 2] [2 3]})))
  (testing "center"
    (is (= (set (neighbors [3 4] [1 1]))
           #{[0 0] [0 1] [0 2]
             [1 0]       [1 2]
             [2 0] [2 1] [2 2]}))))

(defn step-test
  [c n]
  (is (= (step [10 10] c) n)))

(deftest still-lifes
  (testing "Block"
    (let [c #{[1 1] [1 2]
              [2 1] [2 2]}]
      (step-test (step [10 10] c) c)))
  (testing "Beehive"
    (let [c #{      [1 2] [1 3]
              [2 1]             [2 4]
                    [3 2] [3 3]}]
      (step-test c c)))
  (testing "Loaf"
    (let [c #{      [1 2] [1 3]
              [2 1]             [2 4]
                    [3 2]       [3 4]
                          [4 3]}]
      (step-test c c))))

(deftest Oscillators
  (testing "Blinker"
    (step-test #{[1 2]
                 [2 2]
                 [3 2]}
               #{[2 1] [2 2] [2 3]}))
  (testing "Toad"
    (step-test #{      [2 2] [2 3] [2 4]
                 [3 1] [3 2] [3 3]}
               #{            [1 3]
                 [2 1]             [2 4]
                 [3 1]             [3 4]
                       [4 2]}))
  (testing "Beacon"
    (step-test #{[1 1] [1 2]
                 [2 1] [2 2]
                             [3 3] [3 4]
                             [4 3] [4 4]}
               #{[1 1] [1 2]
                 [2 1]
                                   [3 4]
                             [4 3] [4 4]})))
