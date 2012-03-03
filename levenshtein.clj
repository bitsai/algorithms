(ns levenshtein)

;; Laurent Petit's code from below with small optimization
;; https://gist.github.com/828413
(defn- new-row [prev-row row-elem t]
  (reduce (fn [row [d-1 d e]]
            (conj row (if (= row-elem e)
                        d-1
                        (inc (min (peek row) ;; use only binary min calls
                                  (min d d-1))))))
          [(inc (first prev-row))]
          (map vector prev-row (next prev-row) t)))

(defn levenshtein [s t] 
  (peek (reduce (fn [prev-row s-elem]
                  (new-row prev-row s-elem t)) 
                (vec (range (inc (count t)))) 
                s)))
