(ns map-reduce)

(defn map-reduce [mapper reducer coll]
  (let [n (.availableProcessors (java.lang.Runtime/getRuntime))
        partition-size (java.lang.Math/ceil (/ (count coll) n))
        partitions (partition-all partition-size coll)]
    (reduce reducer (pmap mapper partitions))))
