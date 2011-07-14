(ns sort)

(defn- merge* [coll1 coll2 acc]
  (let [[x & xs] coll1
        [y & ys] coll2]
    (cond (empty? coll1) (concat acc coll2)
          (empty? coll2) (concat acc coll1)
          (< x y) (recur xs coll2 (conj acc x))
          :else (recur coll1 ys (conj acc y)))))

(defn merge-sort [coll]
  (if (< (count coll) 2)
    coll
    (let [n (/ (count coll) 2)]
      (merge* (merge-sort (take n coll))
              (merge-sort (drop n coll))
              []))))

(defn- quick-sort* [coll]
  (if (< (count coll) 2)
    coll
    (let [[pivot & others] coll]
      (concat (quick-sort* (filter #(<= % pivot) others))
              [pivot]
              (quick-sort* (filter #(> % pivot) others))))))

(defn quick-sort [coll]
  (quick-sort* (shuffle coll)))
