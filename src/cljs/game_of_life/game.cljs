(ns game-of-life.game)

(defn neighbors
  ([shape yx] (neighbors (for [dy [-1 0 1]
                               dx [-1 0 1]
                               :when (not= dx dy 0)]
                           [dy dx])
                         shape
                         yx))
  ([deltas shape yx]
    (filter (fn [nb]
              (every? true?
                      (map (fn [p m] (< -1 p m)) nb shape)))
            (map #(vec (map + yx %))
                 deltas))))

(defn lives?
  [alive nl]
  (if alive
      (cond (< nl 2)    false   ; underpopulation
            (<= 2 nl 3) alive   ; live on
            :esle       false)  ; overpopulation
      (if (= nl 3)
          true                  ; reproduction
          false)))

(defn step
  [world cells]
  (->> cells
       (mapcat (partial neighbors world))
       frequencies
       (merge-with + (zipmap cells (iterate identity 0))) ; still need to consider the isolated cells
       (filter (fn [[c nl]]
                 (lives? (contains? cells c) nl)))
       (map first)
       (set)))
