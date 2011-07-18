(ns tests
  (:use [graph])
  (:use [hash-table])
  (:use [map-reduce])
  (:use [sort])
  (:use [tree]))

;; Test graph functions
(def graph {:neighbors {1 [2 5 6]
                        2 [1 3 5]
                        3 [2 4]
                        4 [3 5]
                        5 [1 2 4]
                        6 [1]}})

(let [s (with-out-str (bfs graph 1 (fn [x _] (print x))))]
  (println s))
(let [s (with-out-str (dfs graph 1 (fn [x _] (print x))))]
  (println s))

;; Test hash-table functions
(def *table* (atom (hash-table)))

(println (search @*table* 1))
(swap! *table* insert 1)
(println (search @*table* 1))
(swap! *table* delete 1)
(println (search @*table* 1))

;; Test map-reduce functions
(defn slow-count [coll]
  (loop [xs coll
         cnt 0]
    (if (empty? xs)
      cnt
      (do (java.lang.Thread/sleep 10)
          (recur (rest xs)
                 (inc cnt))))))

(time (println (slow-count (range 100))))
(time (println (map-reduce slow-count + (range 100))))

;; Test sort functions
(println (merge-sort (shuffle (range 10))))
(println (quick-sort (shuffle (range 10))))

;; Test tree functions
(def tree {:neighbors '{B [A D]
                        D [C E]
                        F [B G]
                        G [nil I]
                        I [H]}})

(let [s (with-out-str (bfs tree 'F (fn [x _] (print x))))]
  (println s))
(let [s (with-out-str (dfs tree 'F (fn [x _] (print x))))]
  (println s))
(let [s (with-out-str (pre-order tree 'F (fn [x _] (print x))))]
  (println s))
(let [s (with-out-str (in-order tree 'F (fn [x _] (print x))))]
  (println s))
(let [s (with-out-str (post-order tree 'F (fn [x _] (print x))))]
  (println s))
