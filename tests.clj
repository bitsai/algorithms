(ns tests
  (:require [graph]
            [hash-table]
            [levenshtein]
            [map-reduce]
            [sort]
            [tree]
            [trie])
  (:use [clojure.test]))

;; graph
(def g {:neighbors {1 [2 5 6]
                    2 [1 3 5]
                    3 [2 4]
                    4 [3 5]
                    5 [1 2 4]
                    6 [1]}})

(deftest graph-bfs
  (let [s (with-out-str (graph/bfs g 1 (fn [x _] (print x))))]
    (is (= "125634" s))))

(deftest graph-dfs
  (let [s (with-out-str (graph/dfs g 1 (fn [x _] (print x))))]
    (is (= "123456" s))))

;; hash table
(def ht (atom (hash-table/make-table)))

(deftest hash-table
  (is (nil? (hash-table/search @ht 1)))
  (swap! ht hash-table/insert 1)
  (is (= 1 (hash-table/search @ht 1)))
  (swap! ht hash-table/delete 1)
  (is (nil? (hash-table/search @ht 1))))

;; Levenshtein distance
(deftest levenshtein
  (let [a (apply str (repeat 300 "kitten"))
        b (apply str (repeat 300 "sitting"))]
    (is (= 900 (time (levenshtein/levenshtein a b))))))

;; Map-Reduce
(defn slow-count [coll]
  (loop [xs coll
         cnt 0]
    (if (empty? xs)
      cnt
      (do (java.lang.Thread/sleep 10)
          (recur (rest xs)
                 (inc cnt))))))

(deftest map-reduce
  (is (= 100 (time (slow-count (range 100)))))
  (is (= 100 (time (map-reduce/map-reduce slow-count + (range 100)))))
  (shutdown-agents))

;; sort
(deftest merge-sort
  (is (= (range 10) (sort/merge-sort (shuffle (range 10))))))

(deftest quick-sort
  (is (= (range 10) (sort/quick-sort (shuffle (range 10))))))

;; tree
(def t1 {:neighbors '{B [A D]
                      D [C E]
                      F [B G]
                      G [nil I]
                      I [H nil]}})

(deftest tree-bfs
  (let [s (with-out-str (graph/bfs t1 'F (fn [x _] (print x))))]
    (is (= "FBGADICEH" s))))

(deftest tree-dfs
  (let [s (with-out-str (graph/dfs t1 'F (fn [x _] (print x))))]
    (is (= "FBADCEGIH" s))))

(deftest pre-order
  (let [s (with-out-str (tree/pre-order t1 'F (fn [x _] (print x))))]
    (is (= "FBADCEGIH" s))))

(deftest in-order
  (let [s (with-out-str (tree/in-order t1 'F (fn [x _] (print x))))]
    (is (= "ABCDEFGHI" s))))

(deftest post-order
  (let [s (with-out-str (tree/post-order t1 'F (fn [x _] (print x))))]
    (is (= "ACEDBHIGF" s))))

;; trie
(def t2 (trie/make-trie "the" "their" "there" "was" "when"))

(deftest trie
  (is (true? (trie/in-trie? t2 "the")))
  (is (true? (trie/in-trie? t2 "their")))
  (is (true? (trie/in-trie? t2 "there")))
  (is (true? (trie/in-trie? t2 "was")))
  (is (true? (trie/in-trie? t2 "when")))
  (is (false? (trie/in-trie? t2 "")))
  (is (false? (trie/in-trie? t2 "her")))
  (is (false? (trie/in-trie? t2 "whent"))))

(run-tests)
