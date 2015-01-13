(
define (problem client-server-model)
(:domain client-server)

(:objects
            s1 s2 s3 s4 s5 - server
            db1 db2 - database
            l1 l2 - location
)

(:init
      		(location1 l1)
            (location2 l2)
            (cheaper l1 l2)
            (server-active s1)
            (cheaper l1 l2)
            (server-at s1 l2)
            (server-at s2 l2)
            (server-at s3 l1)
            (server-at s4 l1)
            (server-at s5 l1)
            (databaseA db1)
            (databaseB db2)
            (database-active db1)
            (database-active db2)
            (= (databaseA-threads) 1)
            (= (databaseB-threads) 1)
            (= (max-database-threads) 2)
            (= (server-count) 1)
            (= (server-cost) 10)
            (= (response-time) 125)
            (= (content-quality) 20)
            (= (service-disruption) 0)
            (= (time) 0)
      		(high-fidelity)
)

;(:goal (forall (?r - request) (request-served ?r)))
(:goal (and (<= (response-time) 100) (<= (time) 3000)))
;(:goal (<= (server-cost) 51))
;(:goal (and (<= (server-cost) 50) ( <= (response-time) 100)))

(:metric maximize (+ (+ (* (-0.4) (response-time)) (* (-0.4) (server-cost)))
                   (+ (* (0.1) (content-quality)) (* (-0.1) (service-disruption)))))
;(:metric minimize (total-time))
)

