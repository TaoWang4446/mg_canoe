;(function(window) {

  var svgSprite = '<svg>' +
    '' +
    '<symbol id="icon-guanbi" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M313.012706 210.823529C145.689098 210.823529 10.039216 345.670275 10.039216 512s135.649882 301.176471 302.97349 301.176471h397.994667C878.33098 813.176471 1013.960784 678.329725 1013.960784 512S878.33098 210.823529 711.007373 210.823529H313.012706z m-0.341333 573.861647c-151.853176 0-274.954039-122.368-274.95404-273.327686S160.818196 238.029804 312.671373 238.029804c151.843137 0 274.954039 122.368 274.954039 273.327686S464.51451 784.685176 312.671373 784.685176z" fill="#272536" ></path>' +
    '' +
    '</symbol>' +
    '' +
    '<symbol id="icon-kaiqi" viewBox="0 0 1024 1024">' +
    '' +
    '<path d="M714.24 202.24L309.76 202.24C138.24 202.24 0 340.48 0 512c0 171.52 138.24 309.76 309.76 309.76l407.04 0C885.76 821.76 1024 683.52 1024 512 1024 340.48 885.76 202.24 714.24 202.24L714.24 202.24zM714.24 791.04c-156.16 0-281.6-125.44-281.6-281.6 0-156.16 125.44-281.6 281.6-281.6 156.16 0 281.6 125.44 281.6 281.6C995.84 665.6 870.4 791.04 714.24 791.04L714.24 791.04zM714.24 791.04"  ></path>' +
    '' +
    '</symbol>' +
    '' +
    '</svg>'
  var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
  var shouldInjectCss = script.getAttribute("data-injectcss")

  /**
   * document ready
   */
  var ready = function(fn) {
    if (document.addEventListener) {
      if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
        setTimeout(fn, 0)
      } else {
        var loadFn = function() {
          document.removeEventListener("DOMContentLoaded", loadFn, false)
          fn()
        }
        document.addEventListener("DOMContentLoaded", loadFn, false)
      }
    } else if (document.attachEvent) {
      IEContentLoaded(window, fn)
    }

    function IEContentLoaded(w, fn) {
      var d = w.document,
        done = false,
        // only fire once
        init = function() {
          if (!done) {
            done = true
            fn()
          }
        }
        // polling for no errors
      var polling = function() {
        try {
          // throws errors until after ondocumentready
          d.documentElement.doScroll('left')
        } catch (e) {
          setTimeout(polling, 50)
          return
        }
        // no errors, fire

        init()
      };

      polling()
        // trying to always fire before onload
      d.onreadystatechange = function() {
        if (d.readyState == 'complete') {
          d.onreadystatechange = null
          init()
        }
      }
    }
  }

  /**
   * Insert el before target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var before = function(el, target) {
    target.parentNode.insertBefore(el, target)
  }

  /**
   * Prepend el to target
   *
   * @param {Element} el
   * @param {Element} target
   */

  var prepend = function(el, target) {
    if (target.firstChild) {
      before(el, target.firstChild)
    } else {
      target.appendChild(el)
    }
  }

  function appendSvg() {
    var div, svg

    div = document.createElement('div')
    div.innerHTML = svgSprite
    svgSprite = null
    svg = div.getElementsByTagName('svg')[0]
    if (svg) {
      svg.setAttribute('aria-hidden', 'true')
      svg.style.position = 'absolute'
      svg.style.width = 0
      svg.style.height = 0
      svg.style.overflow = 'hidden'
      prepend(svg, document.body)
    }
  }

  if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
    window.__iconfont__svg__cssinject__ = true
    try {
      document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
    } catch (e) {
      console && console.log(e)
    }
  }

  ready(appendSvg)


})(window)