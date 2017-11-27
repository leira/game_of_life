
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
  (if (pos? c)
      (cond (< liven 2)    0  ; underpopulation
            (<= 2 liven 3) 1  ; live on
            :esle 0)          ; overpopulation
      (if (= liven 3)
          1                   ; reproduction
          0)))

; get-in doesn't realize lazy seq returned from map-indexed-2d
;(defn get-2d
;  [m [y x]]
;  (nth (nth m y) x))

(defn step
  [world]
  (let [warr (to-array-2d world)
        h (count world)
        w (count (first world))]
    (map-indexed-2d 
      (fn [yx v]
        (->> (neighbors [h w] yx)
;             (map #(get-2d world %))
             (map #(get-in warr %))
             (reduce +)
             (rule v)))
      world)))



(defn expand
  ([pattern nsize] (expand pattern nsize [0 0]))
  ([pattern [nh nw] [dy dx]]
    (let [h (count pattern)
          w (count (first pattern))]
      (for [y (range nh)]
        (for [x (range nw)]
          (let [py (- y dy)
                px (- x dx)]
            (if (and (< -1 py h)
                     (< -1 px w))
                (get-in pattern [py px])
                0)))))))
