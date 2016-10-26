# segtree

Clojure implementation of [segment trees](https://en.wikipedia.org/wiki/Segment_tree).

## Usage

FIXME: explanation

    $ java -jar segtree-0.1.0-standalone.jar [args]

## Examples

Defining a simple sum segment tree.

```clojure
(require '[segtree.core :refer :all])
(defmethod join-vals :sum [a b _] (+ a b))
(defmethod identity-val :sum [_] 0)

(-> (build [1 2 3] :sum)
    (query 0 2))

```

## License

Copyright © 2016 Caio Oliveira

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
