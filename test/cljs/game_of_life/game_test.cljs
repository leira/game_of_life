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
  (is (= (step c) n)))

(deftest still-lifes
  (testing "Block"
    (let [c [[0 0 0 0]
             [0 1 1 0]
             [0 1 1 0]
             [0 0 0 0]]]
      (step-test (step c) c)))
  (testing "Beehive"
    (let [c [[0 0 0 0 0 0]
             [0 0 1 1 0 0]
             [0 1 0 0 1 0]
             [0 0 1 1 0 0]
             [0 0 0 0 0 0]]]
      (step-test c c)))
  (testing "Loaf"
    (let [c [[0 0 0 0 0 0]
             [0 0 1 1 0 0]
             [0 1 0 0 1 0]
             [0 0 1 0 1 0]
             [0 0 0 1 0 0]
             [0 0 0 0 0 0]]]
      (step-test c c))))

(deftest Oscillators
  (testing "Blinker"
    (step-test [[0 0 0 0 0]
                [0 0 1 0 0]
                [0 0 1 0 0]
                [0 0 1 0 0]
                [0 0 0 0 0]]

               [[0 0 0 0 0]
                [0 0 0 0 0]
                [0 1 1 1 0]
                [0 0 0 0 0]
                [0 0 0 0 0]]))
  (testing "Toad"
    (step-test [[0 0 0 0 0 0]
                [0 0 0 0 0 0]
                [0 0 1 1 1 0]
                [0 1 1 1 0 0]
                [0 0 0 0 0 0]
                [0 0 0 0 0 0]]

               [[0 0 0 0 0 0]
                [0 0 0 1 0 0]
                [0 1 0 0 1 0]
                [0 1 0 0 1 0]
                [0 0 1 0 0 0]
                [0 0 0 0 0 0]]))
  (testing "Beacon"
    (step-test [[0 0 0 0 0 0]
                [0 1 1 0 0 0]
                [0 1 1 0 0 0]
                [0 0 0 1 1 0]
                [0 0 0 1 1 0]
                [0 0 0 0 0 0]]

               [[0 0 0 0 0 0]
                [0 1 1 0 0 0]
                [0 1 0 0 0 0]
                [0 0 0 0 1 0]
                [0 0 0 1 1 0]
                [0 0 0 0 0 0]])))
