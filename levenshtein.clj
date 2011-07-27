(ns levenshtein)

;; http://en.wikipedia.org/wiki/Levenshtein_distance#Computing_Levenshtein_distance
(defn levenshtein [^String s ^String t]
  (let [m (count s)
        n (count t)
        ^objects d (make-array Integer/TYPE (inc m) (inc n))]
    (doseq [i (range (inc m))]
      (aset-int d i 0 i))
    (doseq [j (range (inc n))]
      (aset-int d 0 j j))
    (doseq [j (range 1 (inc n))
            i (range 1 (inc m))]
      (let [i-1 (dec i)
            j-1 (dec j)
            ^ints row-i (aget d i)
            ^ints row-i-1 (aget d i-1)]
        (aset-int row-i j (if (= (.charAt s i-1) (.charAt t j-1))
                            (aget row-i-1 j-1)
                            (inc (min (aget row-i-1 j)
                                      (min (aget row-i j-1)
                                           (aget row-i-1 j-1))))))))
    (aget d m n)))
