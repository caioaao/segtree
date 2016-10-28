# segtree

Clojure implementation of [segment trees](https://en.wikipedia.org/wiki/Segment_tree) with (optionally) lazy propagation.

All operations run in `O(k log N)`, where `k` is the complexity of the user-implemented operations. Building the segment tree takes `O(k N log N)`.

## Examples

For examples on how to implement your own segment trees, check [the tests file](test/segtree/core_test.clj).

Available operations are defined [here](src/segtree/core.clj).

## License

Copyright © 2016 Caio Oliveira

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
