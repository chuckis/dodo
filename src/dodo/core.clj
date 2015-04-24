(ns dodo.core
  (:use compojure.core)
  (:require [ring.middleware.params :as params]
            [ring.util.response :as response]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [net.cgrand.enlive-html :as html]))

(def nav-items
  {"Home" "/"
   "Все задания" "/all_tasks"
   "исполнители" "/bunch"})

(html/defsnippet header "templates/header.html"
  [:body :div.navbar]
  [current-path]
  [:a.brand] (html/content "Dodo - local tasks")
  [:ul.nav [:li html/first-of-type]] (html/clone-for [[caption url] nav-items]
                                                     [:li] (if (= current-path url)
                                                             (html/set-attr :class "active")
                                                             identity)
                                                     [:li :a] (html/content caption)
                                                     [:li :a] (html/set-attr :href url)))

(html/deftemplate main-template "templates/application.html"
  [{:keys [path]}]
  [:head :title] (html/content "DOLOTO")
  [:body] (html/do-> (html/append (header path))))

(defn index-page
  [request]
  (main-template {:path (:uri request)}))

(defroutes main-routes
  (GET "/" request (index-page request))
  (route/not-found "ups, page not found"))

(def app
  (-> (handler/site main-routes)))
