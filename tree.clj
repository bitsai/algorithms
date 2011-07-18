(ns tree)

(defn pre-order [tree node f]
  (let [[left-child right-child] ((:neighbors tree) node)]
    (f node tree)
    (when left-child
      (pre-order tree left-child f))
    (when right-child
      (pre-order tree right-child f))))

(defn in-order [tree node f]
  (let [[left-child right-child] ((:neighbors tree) node)]
    (when left-child
      (in-order tree left-child f))
    (f node tree)
    (when right-child
      (in-order tree right-child f))))

(defn post-order [tree node f]
  (let [[left-child right-child] ((:neighbors tree) node)]
    (when left-child
      (post-order tree left-child f))
    (when right-child
      (post-order tree right-child f))
    (f node tree)))
