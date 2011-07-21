(ns map-reduce)

(defn map-reduce [mapper reducer coll]
  (let [n (.availableProcessors (Runtime/getRuntime))
        partition-size (Math/ceil (/ (count coll) n))
        partitions (partition-all partition-size coll)]
    (reduce reducer (pmap mapper partitions))))
