(ns graph)

(defn bfs [graph start f]
  (loop [visited #{}
         [n & ns] [start]]
    (when n
      (let [new-visited (conj visited n)
            neighbors (remove nil? ((:neighbors graph) n))
            new-queue (remove new-visited (concat ns neighbors))]
        (f n graph)
        (recur new-visited new-queue)))))

(defn dfs [graph start f]
  (loop [visited #{}
         [n & ns] [start]]
    (when n
      (let [new-visited (conj visited n)
            neighbors (remove nil? ((:neighbors graph) n))
            new-stack (remove new-visited (concat neighbors ns))]
        (f n graph)
        (recur new-visited new-stack)))))
