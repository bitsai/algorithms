(ns hash-table)

(def m 256)

(defn make-table []
  (vec (repeat m '())))

(defn hash-key [x]
  (rem (hash x) m))

(defn search [table x]
  (let [k (hash-key x)
        bucket (nth table k)]
    (some #{x} bucket)))

(defn insert [table x]
  (let [k (hash-key x)
        bucket (nth table k)]
    (if (some #{x} bucket)
      table
      (assoc table k (conj bucket x)))))

(defn delete [table x]
  (let [k (hash-key x)
        bucket (nth table k)]
    (if (some #{x} bucket)
      (assoc table k (remove #{x} bucket))
      table)))
