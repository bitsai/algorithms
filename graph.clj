(ns graph)

(defn bfs [adj-list start f]
  (loop [visited #{}
         [n & ns] [start]]
    (when n
      (let [new-visited (conj visited n)
            new-queue (remove new-visited (concat ns (adj-list n)))]
        (f n)
        (recur new-visited new-queue)))))

(defn dfs [adj-list start f]
  (loop [visited #{}
         [n & ns] [start]]
    (when n
      (let [new-visited (conj visited n)
            new-stack (remove new-visited (concat (adj-list n) ns))]
        (f n)
        (recur new-visited new-stack)))))
