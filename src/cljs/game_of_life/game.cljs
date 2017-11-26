
(ns game-of-life.game)

(defn neighbors
  ([shape yx] (neighbors (for [dy [-1 0 1]
                               dx [-1 0 1]
                               :when (not= dx dy 0)]
                           [dy dx])
                         shape
                         yx))
  ([deltas shape yx]
    (filter (fn [[ny nx]]
              (let [[h w] shape]
                (and (< -1 ny h)
                     (< -1 nx w))))
            (map #(vec (map + yx %))
                 deltas))))

(defn map-indexed-2d
  [f mtx]
  (map-indexed
    (fn [y row]
      (map-indexed
        (fn [x v]
          (f [y x] v))
        row))
    mtx))

(defn rule
  [c liven]
  (if (= c 1)
      (cond (< liven 2)    0  ; underpopulation
            (<= 2 liven 3) 1  ; live on
            :esle 0)          ; overpopulation
      (if (= liven 3)
          1                   ; reproduction
          0)))

(defn step
  [world]
  (let [h (count world)
        w (count (first world))]
    (map-indexed-2d 
      (fn [yx v]
        (->> (neighbors [h w] yx)
             (map #(get-in world %))
             (reduce +)
             (rule v)))
      world)))
