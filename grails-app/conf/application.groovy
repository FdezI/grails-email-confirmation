grails.doc.title = "Email Confirmation"
grails.doc.subtitle = "Email Confirmation - for sending and verifying confirmation emails"
grails.doc.images = new File("resources/img")
grails.doc.authors = "Marc Palmer (marc@grailsrocks.com)"
grails.doc.license = "ASL 2"
grails.doc.copyright = "&copy; 2013 Marc Palmer"
grails.doc.footer = "Please contact the author with any corrections or suggestions"
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"

shiro.enabled = false
shiro.web.enabled = false

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
    debug 'com.grailsrocks',
          'org.grails.plugin.platform',
          'grails.app.controller',
          'grails.app.service'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}



/*
 * These events are sent to the application scope by default
 * Plugins can supply a custom namespace and event when requestion a confirmation using event + eventNamespace args
 * and if supplied these events will be send using the event as a prefix i.e. "signup.confirmed" if the event was passed as "signup"
 */
events = {
    /*
     * Triggered when a confirmation occurs.
     *
     * @param event object has properties:
     *    confirmationEvent - application defined event name for the confirmation
     *    email - email address that confirmed
     *    id - optional application defined id string relating to the kind of confirmation
     *
     * @return Receiver must return a Map which will be used to redirect() the user to a new page
     */
    confirmed(requiresReply:true)

    /*
     * Sent when a confirmation is attempted by a user, but the token is invalid, already consumed or otherwise broken
     *
     * @param event object has properties:
     *    confirmationEvent - application defined event name for the confirmation
     *    token - the email confirmation token that the user supplied in their link, which was not recognized
     *
     * @return Receiver must return a Map which will be used to redirect() the user to a new page
     */
    invalid()

    /*
     * Sent when a confirmation has lapsed with no successful attempt made by the user to confirm.
     *
     * @param event object has properties:
     *    confirmationEvent - application defined event name for the confirmation
     *    email - email address that timed out
     *    id - optional application defined id string relating to the kind of confirmation
     *
     * @return Nothing
     */
    timeout()
}