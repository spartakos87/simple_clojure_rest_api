(ns clj-rest2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults] ]
            )


  )


(defn scrample? [str1 str2]
   ; First we check if str1 and str2 include only lowercase characters
   ; in any other case we will return false

  ( if (and (every? #(Character/isLowerCase %)  str1) (every? #(Character/isLowerCase %) str2))
   (do

    ; Secondly we check which one of str1 or str2 is contain the most characters
        (if (< (count str1) (count str2))
          (scrample? str2 str1)
          (do
                                        ; Then we check if the 'biggest' string is superset of the other
            ; so we check if the biggest string contains all characters of the 'small' one
          (if (clojure.set/superset? (set str1) (set  str2))

            (do
                                        ; and here first of all convert str1 and str2 to hash-maps with key each character which
                                        ; contain and value the times of them
              ; so the superset ,str1 in our case must have equal or more character of str2 in other case the result of this is false
  (if (= 0  (count (filter #(= false %) (map #(if (>= (get (frequencies str1)  (first %)) (second %)) true false) (frequencies str2)))))
true
false

)
)

            false

            )))


        )
   false

   )



        )
                  

(defroutes app-routes
  (GET "/" [] "")
  (GET "/scrample" [str1 str2]
;(str (type str1))
;when I try as link if I use read-string I get the right result
              ;(str (scrample? (read-string str1) (read-string str2)))
;when I make the get request I should use .toString
              (str (scrample? (.toString str1) (.toString str2)))
;In any other case I get to additional character "" at the begin of each string
       )
  (route/not-found "Not Found"))





(def app
  (wrap-defaults app-routes site-defaults)

  )
