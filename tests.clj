(ns tests
  (:use [graph])
  (:use [map-reduce])
  (:use [sort]))

;; Test graph functions
(def graph {:neighbors {1 [2 5 6]
                        2 [1 3 5]
                        3 [2 4]
                        4 [3 5]
                        5 [1 2 4]
                        6 [1]}})

(bfs graph 1 (fn [x g] (println x)))
(dfs graph 1 (fn [x g] (println x)))

;; Test map-reduce functions
(defn slow-count [coll]
  (loop [xs coll
         cnt 0]
    (if (empty? xs)
      cnt
      (do (java.lang.Thread/sleep 10)
          (recur (rest xs)
                 (inc cnt))))))

(time (slow-count (range 100)))
(time (map-reduce slow-count + (range 100)))

;; Test sort functions
(println (merge-sort (shuffle (range 10))))
(println (quick-sort (shuffle (range 10))))
