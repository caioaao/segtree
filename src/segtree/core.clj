(ns segtree.core)

(defmulti join-vals (fn [_ _ type] type))
(defmulti identity-val (fn [tree] (:type tree)))

(defn join-trees [left right]
  {:type      (:type left)
   :value     (join-vals (:value left) (:value right) (:type left))
   :left      left
   :right     right
   :idx-left  (:idx-left left)
   :idx-right (:idx-right right)})

(defn leaf [value idx tree-type]
  {:type        tree-type
   :value       value
   :idx-left  idx
   :idx-right idx})

(defn build
  ([arr tree-type]
   (build arr 0 (dec (count arr)) tree-type))
  ([arr i j tree-type]
   (if (= i j)
     (leaf (get arr i) i tree-type)
     (join-trees (build arr i (quot (+ i j) 2) tree-type)
                 (build arr (inc (quot (+ i j) 2)) j tree-type)))))

(defn query
  "Returns result of joining all values between `a` and `b`."
  [tree a b]
  (cond
    (or (< (:idx-right tree) a)
        (> (:idx-left tree) b))
    (identity-val tree)

    (and (>= (:idx-left tree) a)
         (<= (:idx-right tree) b))
    (:value tree)

    :true
    (join-vals (query (:left tree) a b)
               (query (:right tree) a b)
               (:type tree))))

(defn point-update
  "Joins `v` with value in `idx`."
  [tree idx v]
  (cond
    (or (< (:idx-right tree) idx)
        (> (:idx-left tree) idx))
    tree

    (and (= (:idx-left tree) idx)
         (= (:idx-right tree) idx))
    (update tree :value join-vals v (:type tree))

    :true
    (join-trees (point-update (:left tree) idx v)
                (point-update (:right tree) idx v))))
