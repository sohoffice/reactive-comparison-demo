Locust load tests
=================

Special flags
-------------

--once The locust user stop when first task is finished.

Launch headless
---------------

```
locust --headless -H http://localhost:8080 -f locustfile.py -u 1 -r 1 -t 1s
```

The above will run 1 user, 1 spawn rate for 1 second in headless mode.
