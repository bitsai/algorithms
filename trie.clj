(ns trie)

(defn make-trie [& strs]
  (reduce (fn [trie s] (assoc-in trie (concat s [:word?]) true))
          {}
          strs))

(defn in-trie? [trie s]
  (let [[c & cs] s
        branch (get trie c)]
    (cond (and (empty? s) (:word? trie)) true
          branch (recur branch cs)
          :else false)))
