
var APP = new Object();
//APP.BASEURL = "http://pa.ptlinfotech.in/";
APP.BASEURL = window.location.origin + "/PA/";
APP.API_VERSION = "apis/";
APP.APP_VERSION = "1.0";
APP.MSGTEXT = "";
APP.SMS_LIST = [];
APP.checkConnection = false;

document.addEventListener("deviceready", function() {
    var exitCount = 0;
    document.addEventListener("backbutton", function() {
        var $custom_sbox = $(".custom_sbox:visible");
        if ($custom_sbox.length > 0 || $(".gldp-flatwhite").is(":visible")) {
            $(".a_black_layer.blackbg").trigger("click");
            return;
        }
        if ($("#menu-left").is(":visible") || $(".mm-menu.right").is(":visible")) {
            $('.a_black_layer.menuLayer', ".pt-current-page").trigger("click");
            return;
        }
        var $goBack = $("header .goBack:visible", ".pt-current-page");
        if ($goBack.length > 0) {
            if ($goBack.attr("href") && $goBack.attr("href").indexOf(".html") > 0) {
                window.location.href = $goBack.attr("href");
            } else {
                $goBack.trigger("click");
            }
        } else {
            exitCount++;
            if (exitCount == 2) {
                navigator.app.exitApp();
            }
            setMessage("Please tap twice to exit", 3);
            window.setTimeout(function() {
                exitCount = 0;
            }, 300);
        }
    }, false);

    var current_utl = window.location.href;
//    if (current_utl.indexOf("forgotpassword") != -1 || current_utl.indexOf("signup") != -1 || current_utl.indexOf("profile") != -1) {
////      https://github.com/floatinghotpot/cordova-plugin-sms
//        if (SMS)
//            SMS.startWatch(function() {
//                //update('watching', 'watching started');
//            }, function() {
//                //  updateStatus('failed to start watching');
//            });
//        document.addEventListener('onSMSArrive', function(e) {
//            var data = e.data;
//            APP.SMS_LIST.push(data);
//            var $ptcurrentpage = $(".pt-current-page:visible");
//            if (data.body.toLowerCase().indexOf("family book") > -1) {
//                var otp = /\d+/g.exec(data.body)[0];
//                $("[name=otp]", $ptcurrentpage).val(otp);
//            }
//        });
//    }

//    https://www.npmjs.com/package/cordova-plugin-appversion
    if (AppVersion.version) {
        APP.APP_VERSION = AppVersion.version;
    }

//    https://github.com/danwilson/google-analytics-plugin
    var appdevice = device;
    if (window.analytics) {
        window.analytics.startTrackerWithId('UA-74526602-1');
        window.analytics.setUserId(appdevice.uuid);
        window.analytics.trackView(current_utl);
    }

    APP.checkConnection = true;
    isConnectionLive();
}, false);

function isConnectionLive() {
    if (!APP.checkConnection) {
        return true;
    }
    APP.checkConnection = false;
    window.setTimeout(function() {
        APP.checkConnection = true;
    }, 5000);
    if (navigator && navigator.connection && navigator.connection.type != 0) {
        if (typeof Connection != "undefined" && navigator.connection.type == Connection.NONE) {
//            setMessage("No Internet Connection, try again..", 2);
            var $nointernetconn = $(".nointernetconn_container");
            if ($nointernetconn.length == 0) {
                jqueryExternalAjax("nointernetconnection.html", "", function(res) {
                    $nointernetconn = $("<div class='nointernetconn_container' />");
                    $nointernetconn.html(res).css("height", $(window).height());
                    $('body').append($nointernetconn);
                    var onTapReload = function() {
                        window.location.reload();
                    };
                    $("#touch_reload", $nointernetconn).click(onTapReload);
                });
            }
            return false;
        }
    }
    return true;
}

function jqueryAjax(url, param, callback, errorFn) {
    isConnectionLive();
    if (localStorage.getItem("isLogin")) {
        param["sessionid"] = jQuery.parseJSON(localStorage.getItem("user")).DATA.sessionid;
    }
    jQuery.ajax({
        url: APP.BASEURL + url,
        method: "GET",
        data: param,
        success: callback,
        error: function(xhr) {
            oLoadingDiv.hide();
            setMessage("something went wrong.", 2);
            if (errorFn) {
                errorFn(xhr);
            }
        }
    });
}

function jqueryExternalAjax(url, param, callback, errorFn) {
    jQuery.ajax({
        url: url,
        data: param,
        success: callback,
        error: function(xhr) {
            oLoadingDiv.hide();
        }
    });
}

function getParamObj($container) {
    var param = new Object();
    $("input[type=text],input[type=tel],input[type=hidden],input[type=password],select,input[type=checkbox],input[type=radio],textarea", $container).each(function() {
        var $this = $(this);
        if ($this.attr('name') && $this.attr('name').length > 0) {
            param[$this.attr('name')] = $.trim($this.val());
        }
    });
    return param;
}

LoadingBox = function() {
    var $blackLayer = $("<div id='loading_layer'/>").attr("class", "a_black_layer loadingLayer");

    var $loadingDiv = $("<div id='a_loading_layer'><center><img src='images/load.gif' /></center><div>Loading..</div></div>")
            .attr("class", "loadingPop");

    this.show = function(bDisplayLayer, isFull) {
        var $mainContainer = $("body");
        if ($mainContainer.find("#a_loading_layer").length == 0) {
            $mainContainer.append($loadingDiv);
        }
        $blackLayer.add($loadingDiv).css("display", "block");
        if (isFull) {
            $blackLayer.addClass("blackbgfull");
        }
        if (bDisplayLayer) {
            if ($mainContainer.find("#loading_layer").length == 0) {
                $mainContainer.append($blackLayer);
            }
            $blackLayer.css("display", "block");
        }
    };
    this.hide = function() {
        $blackLayer.css("display", "none");
        $loadingDiv.css("display", "none");
    };
};
var oLoadingDiv = new LoadingBox();

var setMessage = function(msg, type, timeout, $errorBox) {
    if (APP.MSGTEXT && APP.MSGTEXT == msg) {
        return;
    } else {
        APP.MSGTEXT = msg;
    }

    var $message = $("<div class='messageBox' />");
    if (type && type == 2) {
        $message.addClass("error");
        if ($errorBox) {
            if (!$errorBox.is("select")) {
                $errorBox = $errorBox.parent("div");
            }
            $errorBox.addClass("inputerror");
            setTimeout(function() {
                $errorBox.removeClass("inputerror");
            }, 4000);

            $errorBox.unbind("click").click("focus", function() {
                $errorBox.removeClass("inputerror");
            });
        }
    } else if (type && type == 3) {
        $message.addClass("success");
    }

    var $messageBox = $(".messageBox");
    if ($messageBox.length > 0) {
        var newTop = $messageBox.last().position().top + $messageBox.last().height() + 5;
        $message.css("top", newTop + "px");
    }
    $("body").append($message);

    var $content = $("<div />").html(msg);
    $message.append($content).fadeIn();

    if (!timeout) {
        timeout = 3000;
    }
    setTimeout(function() {
        $message.fadeOut(function() {
            $message.remove();
            APP.MSGTEXT = null;
        });
    }, timeout);
};

windowWidth = $(window).width();
windowHeight = $(window).height();
function changePage($oldPage, $newPage, $backDiv, isBack, onChange, onBack) {
    var onBackClick = function() {
        oLoadingDiv.hide();
        $newPage.animate({"margin-left": windowWidth + "px"}, 400, "easeOutExpo");
        $oldPage.css("margin-left", -windowWidth + "px").addClass("pt-current-page").animate({"margin-left": 0 + "px"}, 400, "easeOutExpo", function() {
            $newPage.removeClass("pt-current-page");
            if (onBack) {
                onBack();
            }
        });
        if ($backDiv) {
            $backDiv.closest("header").parent().find(".loding_container").css("display", "none");
        }
    };
    if ($backDiv) {
        $backDiv.unbind("click.back").bind("click.back", onBackClick);
    }
    if (isBack) {
        onBackClick();
    } else {
        $oldPage.animate({"margin-left": -windowWidth + "px"}, 400, "easeOutExpo");
        $newPage.css("margin-left", windowWidth + "px").addClass("pt-current-page").animate({"margin-left": 0 + "px"}, 400, "easeOutExpo", function() {
            $oldPage.removeClass("pt-current-page");
            if (onChange) {
                onChange();
            }
        });
    }
    $("html,body").animate({scrollTop: "0px"}, 400);
}

APP.ConcurrentReq = false;
APP.BlockConcurrentReq = function(timeout) {
    if (!timeout) {
        timeout = 10000;
    }
    if (APP.ConcurrentReq) {
        return true;
    }
    APP.ConcurrentReq = true;
    window.setTimeout(function() {
        APP.ConcurrentReq = false;
    }, timeout);
    return false;
};

APP.ResetBlockConcurrentReq = function(timeout) {
    window.setTimeout(function() {
        APP.ConcurrentReq = false;
    }, timeout);
};

function initLogin() {
    if (localStorage.getItem("isLogin")) {
        window.location = "home.html";
    }
    var $login = $(".login");
    var onSubmit = function() {
        var param = getParamObj($login);
        param["action"] = "login";
        var username = $.trim(param["username"]);
        var password = $.trim(param["password"]);
        if (username.length == 0 || password.length == 0) {
            setMessage("You can't leave username or password blank", 2);
            return;
        }
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "user.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS == 1) {
                localStorage.setItem("isLogin", true);
                localStorage.setItem("user", responseText);
                window.location = "home.html";
            } else {
                setMessage(res.MESSAGE, 2);
            }
        });
    };
    $("#login_btn", $login).click(onSubmit);
}

function logOut() {
    var param = new Object();
    param["action"] = "logout";
    param["sessionid"] = jQuery.parseJSON(localStorage.getItem("user")).DATA.sessionid;
    oLoadingDiv.show(true, true);
    jqueryAjax(APP.API_VERSION + "user.php", param, function(responseText) {
        localStorage.removeItem("isLogin");
        localStorage.removeItem("user");

        localStorage.removeItem("item_data_list");
        localStorage.removeItem("salesOrderList");
        localStorage.removeItem("purchaseOrderList");
        localStorage.removeItem("purchase_party");
        window.location = "login.html";
    });
    setTimeout(function() {
        localStorage.removeItem("isLogin");
        localStorage.removeItem("user");

        localStorage.removeItem("item_data_list");
        localStorage.removeItem("salesOrderList");
        localStorage.removeItem("purchaseOrderList");
        localStorage.removeItem("purchase_party");
        window.location = "login.html";
    }, 1000);
}

function initIndex() {
    var checkTime = function() {
        window.location = "login.html";
    };
    window.setTimeout(checkTime, 3000);
}

function initMainMenu($menu_container) {
    var $b_layer = $('.a_black_layer.menuLayer', $menu_container);
    if ($b_layer.length === 0) {
        $b_layer = $('<div class="a_black_layer menuLayer"></div>');
        $menu_container.append($b_layer);
    }
    var $menu_left = $('#menu-left', $menu_container);
    var menuLeftWidth = 0;
    $("a[data-menu='menu-left']", $menu_container).click(function() {
        menuLeftWidth = $menu_left.width();
        if (menuLeftWidth > 260) {
            menuLeftWidth = 260;
        }
        var isLowerAndroidVersion = (localStorage.getItem("isLowerAndroidVersion") != null && localStorage.getItem("isLowerAndroidVersion") == "true");
        if (isLowerAndroidVersion) {
            $menu_left.css("height", $(document).height() + "px");
        } else {
            $menu_left.css("overflow-y", "auto");
            $("#dashBoard").css({"height": $(window).height() - 84 + "px", "overflow": "hidden"});//84 dashBoard padding
        }
        $menu_left.css({"left": -menuLeftWidth + "px", "width": menuLeftWidth + "px", "display": "block"}).animate({"left": "0px"}, 400, "easeOutExpo");
        $b_layer.addClass("blackbg").fadeIn(300);
    });

    var $menu_right = $('.mm-menu.right', $menu_container);
    $("a[data-menu='menu-right']", $menu_container).click(function() {
        $menu_right.add($b_layer.removeClass("blackbg")).css("display", "block");
    });
    $("ul li", $menu_right).click(function() {
        $b_layer.add($menu_right).css("display", "none");
    });

    $b_layer.click(function() {
        $("#dashBoard").removeAttr("style");
        if ($menu_right.is(":visible")) {
            $b_layer.add($menu_right).css("display", "none");
        } else if ($menu_left.is(":visible")) {
            if ($("html").scrollTop() > 0) {
                $("html,body").animate({scrollTop: "0px"}, 300);
            }
            $menu_left.animate({"left": -(menuLeftWidth + 10) + "px"}, 400, "easeOutExpo", function() {
                $menu_left.css("display", "none");
            });
            $b_layer.fadeOut(300);
        }
    });
}

//function loadPage(url) {
//    var $home_page = $("#home_page");
//    jqueryExternalAjax(url, "", function(responseText) {
////        APP.ResetBlockConcurrentReq(0);
//        var $new_page = $("#new_page");
//        $new_page.html(responseText);
////        initLowerAndroidVersion();
//        $(".a_black_layer.menuLayer.blackbg").trigger("click");
//        changePage($home_page, $new_page, $(".goBack", $new_page), "", function() {
//        }, function() {
//            $new_page.html("");
//        });
//    });
//}

function loadPage(url, pageId, callback) {
    var $home_page = $(".pt-page.pt-current-page:visible:last");
    if (!pageId) {
        pageId = "new_page";
    }
    oLoadingDiv.show(true, true);
    jqueryExternalAjax(url, "", function(responseText) {
        oLoadingDiv.hide();
        var $new_page = $("<div />").addClass("pt-page").attr("id", pageId);
        $home_page.after($new_page);
        $new_page.html(responseText);
        $(".a_black_layer.menuLayer.blackbg").trigger("click");
        changePage($home_page, $new_page, $(".goBack", $new_page), "", function() {
        }, function() {
            $new_page.remove();
        });
        if (callback) {
            callback();
        }
    });
}

function initHome() {
    var $home_page = $("#home_page");
    var $userProfile = $(".userProfile");
    var user = jQuery.parseJSON(localStorage.getItem("user")).DATA;
    $(".left p", $userProfile).html(user.name + "<br><a>" + user.usertype + "</a>");

    $("#main_page").on("click", "[data-page]", function() {
        var $this = $(this);
        var page = $this.data("page");
        var id = $this.data("id");
        if (!page || !id) {
            return;
        }
        if ($("#menu-left").is(":visible")) {
            $(".menuLayer").trigger("click");
        }
        loadPage(page, id);
    });
}

function initSales() {
    var $sales_home = $("#sales_home");
    var $party_list_main = $("#party_list_main");
    $("[name='billdate'],[name=due_date]", $sales_home).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    }).val(new Date().format("yyyy-MM-dd"));

    var lastSalseBillNo = function() {
        var param = new Object();
        param["action"] = "getbillno";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "sales.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            var billno = res.DATA;
            if (billno) {
//                var billobj = billno.split("/");
//                billno = parseInt(billobj[1]) + 1;
//                var oldmonth = parseInt(billobj[0]);
//                var month = new Date().getMonth() + 1;
//                if (month != oldmonth) {
//                    billno = 1;
//                }
                $("[name=billno]").val(billno);
            }
        });
    };
    lastSalseBillNo();

// item list & search start
    var $item_list_page = $("#item_list_page");
    var $item_list = $(".report.party_list ul", $item_list_page);
    var setItemData = function(DATA) {
        $item_list.html("");
        $.each(DATA, function() {
            var list = $(this)[0];
            $item_list.append('<li><a data-id="' + list.id + '" data-name="' + list.name + '">' + list.name + '</a></li>');
        });
    };
    var setItemCache = function() {
        var item_data_list = localStorage.getItem("item_data_list");
        if (!item_data_list) {
            return;
        }
        var res = jQuery.parseJSON(item_data_list);
        setItemData(res.DATA);
    };
    setItemCache();
    var getItemList = function() {
        var param = new Object();
        param["action"] = "getItemList";
        jqueryAjax(APP.API_VERSION + "item.php", param, function(responseText) {
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                return;
            }
            localStorage.setItem("item_data_list", responseText);
            setItemData(res.DATA);
        });
    };
    getItemList();

    $item_list.on("click", "[data-id]", function() {
        var $this = $(this);
        var id = $this.data("id");
        var name = $this.data("name");
        if (!id || !name) {
            return;
        }
        $("[name=item_id]", $sales_home).val(id);
        $("[name=item_name]", $sales_home).val(name);
        $(".goBack", $item_list_page).trigger("click");
    });

    $("[name=item_name]", $sales_home).click(function() {
        changePage($sales_home, $item_list_page, $(".goBack", $item_list_page));
    });

    $item_list_page.on("input", "[name=searchitem]", function() {
        var item_data_list = localStorage.getItem("item_data_list");
        var $this = $(this);
        if (!item_data_list) {
            return;
        }
        item_data_list = jQuery.parseJSON(item_data_list);
        var data = $.grep(item_data_list.DATA, function(e) {
            if (e.name.toLowerCase().indexOf($this.val().toLowerCase()) > -1) {
                return e;
            }
        });
        setItemData(data);
    });
    // item list & search end

    // party list start
    var $party_list = $(".party_list ul", $party_list_main);
    var setPartyData = function(DATA) {
        $party_list.html("");
        $.each(DATA, function() {
            var list = $(this)[0];
            $party_list.append('<li><a data-id="' + list.id + '" data-name="' + list.name + '">' + list.name + '</a></li>');
        });
    };
    var setSalesParty = function() {
        var sales_party = localStorage.getItem("sales_party");
        if (!sales_party) {
            return;
        }
        var res = jQuery.parseJSON(sales_party);
        setPartyData(res.DATA);
    };
    setSalesParty();

    $party_list_main.on("input", "[name=searchparty]", function() {
        var sales_party = localStorage.getItem("sales_party");
        var $this = $(this);
        if (!sales_party) {
            return;
        }
        sales_party = jQuery.parseJSON(sales_party);
        var data = $.grep(sales_party.DATA, function(e) {
            if (e.name.toLowerCase().indexOf($this.val().toLowerCase()) > -1) {
                return e;
            }
        });
        setPartyData(data);
    });

    var getPurchasePartyList = function() {
        var param = new Object();
        param["action"] = "getpartyList";
        param["type"] = "SALES_PARTY";
        jqueryAjax(APP.API_VERSION + "party.php", param, function(responseText) {
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                return;
            }
            localStorage.setItem("sales_party", responseText);
            if ($("li", $party_list).length == 0) {
                setSalesParty();
            }
        });
    };
    getPurchasePartyList();

    $("[name=party_name]", $sales_home).click(function() {
        changePage($sales_home, $party_list_main, $(".goBack", $party_list_main));
    });

    $party_list.on("click", "[data-name]", function() {
        var $this = $(this);
        $("[name=party_id]", $sales_home).val($this.data("id"));
        $("[name=party_name]", $sales_home).val($this.data("name"));
        $(".goBack", $party_list_main).trigger("click");
    });
    // party list end

    $sales_home.on("click", ".fix_btn .btn", function() {
        var param = getParamObj($sales_home);
        if (!param["party_name"] || !param["party_id"]) {
            setMessage("Select Valid party", 2);
            return;
        }
        if (!param["item_name"] || !param["item_id"]) {
            setMessage("Select Valid Item", 2);
            return;
        }
        param["action"] = "insert";
        param["is_paid"] = $("[name=is_paid]:checked", $sales_home).val();
        var r = confirm("Are you sure to create order!");
        if (r == true) {
            if (APP.BlockConcurrentReq(5000)) {
                return;
            }
            oLoadingDiv.show(true, true);
            jqueryAjax(APP.API_VERSION + "sales.php", param, function(responseText) {
                oLoadingDiv.hide();
                var res = jQuery.parseJSON(responseText);
                if (res.STATUS != 1) {
                    setMessage(res.MESSAGE, 2);
                    return;
                }
                $(".goBack", $sales_home).trigger("click");
            });
        }
    });
}

function initPurchase() {
    var $purchase_home = $("#purchase_home");
    $("[name='date'],[name=due_date]", $purchase_home).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    }).val(new Date().format("yyyy-MM-dd"));

    $purchase_home.on("click", ".fix_btn .btn", function() {
        var param = getParamObj($purchase_home);
        if (!param["party_name"]) {
            setMessage("Enter Valid party", 2);
            return;
        }
        if (!param["name"]) {
            setMessage("Enter Valid Item", 2);
            return;
        }
        if (!param["billno"]) {
            setMessage("Enter Valid billno", 2);
            return;
        }
        if (!param["qty"]) {
            setMessage("Enter Valid Quantity", 2);
            return;
        }
        if (!param["amt"]) {
            setMessage("Enter Valid Amount", 2);
            return;
        }
        param["action"] = "insert";
        var r = confirm("Are you sure to create purchase!");
        if (r == true) {
            if (APP.BlockConcurrentReq(5000)) {
                return;
            }
            oLoadingDiv.show(true, true);
            jqueryAjax(APP.API_VERSION + "purchase.php", param, function(responseText) {
                oLoadingDiv.hide();
                var res = jQuery.parseJSON(responseText);
                if (res.STATUS != 1) {
                    setMessage(res.MESSAGE, 2);
                    return;
                }
                $(".goBack", $purchase_home).trigger("click");
            });
        }
    });
}

function initParty() {
    var $party_home = $("#party_home");
    var $add_party = $("#add_party");
    var $update_party = $("#update_party");
    var $party_list_main = $("#party_list_main");

    $party_home.on("click", "[name=party]", function() {
        changePage($party_home, $party_list_main, $(".goBack", $party_list_main));
    });
    var onSearchClick = function() {
        var param = getParamObj($party_home);
        param["action"] = "getpartyList";
        oLoadingDiv.show(true, true);
        var $party_list = $("#party_list", $party_home).html("");
        jqueryAjax(APP.API_VERSION + "party.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            if (res.DATA.length == 0) {
                $party_list.html("Party Not Found.");
                return;
            }
            $.each(res.DATA, function() {
                var list = $(this)[0];
                var $reportBox = $("<div/>").addClass("reportBox").data("data_map", list);
                var $left = $("<div/>").addClass("left");
                $left.append(list.name + ' <br><b>' + list.mobile + '</b><i class="icon icon-angle-right"></i>');
                $reportBox.append($left, '<div class="clr"></div>');
                $party_list.append($reportBox);
            });
        });
    };
    $party_home.on("click", "#filter .btn", onSearchClick);

    var onPartyAdd = function() {
        var param = getParamObj($add_party);
        param["action"] = "insertparty";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "party.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $("input", $add_party).val("");
            $(".goBack", $add_party).trigger("click");
        });
    };
    $add_party.on("click", ".fix_btn .btn", onPartyAdd);

    $party_home.on("click", ".icon-ios-plus", function() {
        changePage($party_home, $add_party, $(".goBack", $add_party));
    });

    $party_home.on("click", ".reportBox", function() {
        var data_map = $(this).data("data_map");
        if (!data_map) {
            return;
        }
        $("[name=pid]", $update_party).val(data_map.id);
        $("[name=type]", $update_party).val(data_map.type);
        $("[name=name]", $update_party).val(data_map.name);
        $("[name=mobile]", $update_party).val(data_map.mobile);
        $("[name=address]", $update_party).val(data_map.address);
        changePage($party_home, $update_party, $(".goBack", $update_party));
    });

    var onPartyUpdate = function() {
        var param = getParamObj($update_party);
        param["action"] = "updateparty";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "party.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $(".goBack", $update_party).trigger("click");
            $("#filter .btn", $party_home).trigger("click");
        });
    };
    $update_party.on("click", ".fix_btn .btn", onPartyUpdate);
}
function initItem() {
    var $item_home = $("#item_home");
    var $add_item = $("#add_item");
    var $update_item = $("#update_item");

    var $item_list = $("#item_list", $item_home);
    var setItemData = function(DATA) {
        if (DATA.length == 0) {
            $item_list.html("Item Not Found.");
            return;
        }
        $item_list.html("");
        $.each(DATA, function() {
            var list = $(this)[0];
            var $reportBox = $("<div/>").addClass("reportBox").data("data_map", list);
            var $left = $("<div/>").addClass("left");
            $left.append(list.name + '</b><i class="icon icon-angle-right"></i>');
            $reportBox.append($left, '<div class="clr"></div>');
            $item_list.append($reportBox);
        });
    };
    var getItemList = function() {
        var param = new Object();
        param["action"] = "getItemList";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "item.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            localStorage.setItem("item_data_list", responseText);
            setItemData(res.DATA);
        });
    };
    getItemList();

    $item_home.on("input", "[name=searchitem]", function() {
        var item_data_list = localStorage.getItem("item_data_list");
        var $this = $(this);
        if (!item_data_list) {
            return;
        }
        item_data_list = jQuery.parseJSON(item_data_list);
        var data = $.grep(item_data_list.DATA, function(e) {
            if (e.name.toLowerCase().indexOf($this.val().toLowerCase()) > -1) {
                return e;
            }
        });
        setItemData(data);
    });

    var onItemAdd = function() {
        var param = getParamObj($add_item);
        if (param["name"].length == 0) {
            setMessage("please enter item name.", 2);
            return;
        }
        param["action"] = "insert";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "item.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $("input", $add_item).val("");
            $(".goBack", $add_item).trigger("click");
            getItemList();
        });
    };
    $add_item.on("click", ".fix_btn .btn", onItemAdd);

    $item_home.on("click", ".icon-ios-plus", function() {
        changePage($item_home, $add_item, $(".goBack", $add_item));
    });

    $item_home.on("click", ".reportBox", function() {
        var data_map = $(this).data("data_map");
        if (!data_map) {
            return;
        }
        $("[name=item_id]", $update_item).val(data_map.id);
        $("[name=name]", $update_item).val(data_map.name);
        $("[name=dn]", $update_item).val(data_map.dn);
        changePage($item_home, $update_item, $(".goBack", $update_item));
    });

    var onItemUpdate = function() {
        var param = getParamObj($update_item);
        if (param["name"].length == 0) {
            setMessage("please enter item name.", 2);
            return;
        }
        param["action"] = "updateItem";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "item.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $(".goBack", $update_item).trigger("click");
            getItemList();
        });
    };
    $update_item.on("click", ".fix_btn .btn", onItemUpdate);
}

function initSalesReport() {
    var $sales_report = $("#sales_report");
    var $edit_sales_bill = $("#edit_sales_bill");
    var $data_list = $("#data_list", $sales_report);

    $("[name='fromdate'],[name='todate']", $sales_report).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    }).val(new Date().format("yyyy-MM-dd"));
    $("[name='due_date']", $edit_sales_bill).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    });

    var onSalesReportData = function() {
        var param = getParamObj($("#filter", $sales_report));
        param["action"] = "getAll";
        $data_list.html("");
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "sales.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            if (!res.DATA || res.DATA.length == 0) {
                $data_list.html("Record Not Found");
                return;
            }
            var mainTotal = 0;
            $.each(res.DATA, function() {
                var list = $(this)[0];
                var total = list.qty * list.amt;
                var $reportBox = $("<div/>").addClass("reportBox").data("sales_map", list);
                var $left = $("<div/>").addClass("left").append('BillNo: ' + list.bill_no + ' <br><b>' + list.partyname + '</b>');
                var $right = $("<div/>").addClass("right").append('Rs. ' + total);
                var $bottom = $("<div/>").addClass("bottom").append('<h1>' + list.date.split(" ")[0] + '</h1><span class="' + (list.status == "UNPAID" ? "red" : "") + '">' + list.status + '</span>');
                $reportBox.append($left, $right, '<div class="clr"></div>', $bottom, '<div class="clr"></div>');
                $data_list.append($reportBox);
                mainTotal += total;
            });
            $data_list.prepend('<div class="total"><a>Total Sales: Rs. ' + mainTotal + '</a></div><div class="clr"></div>');
        });
    };
    $sales_report.on("click", "#filter .btn", onSalesReportData);

    $sales_report.on("click", ".reportBox", function() {
        var sales_map = $(this).data("sales_map");
        if (!sales_map) {
            return;
        }
        $("[name='id']", $edit_sales_bill).val(sales_map.id);
        $("[name='party_id']", $edit_sales_bill).val(sales_map.party_id);
        $("[name='party_name']", $edit_sales_bill).val(sales_map.partyname);
        $("[name='item_id']", $edit_sales_bill).val(sales_map.item_id);
        $("[name='item_name']", $edit_sales_bill).val(sales_map.itemname);
        $("[name='billno']", $edit_sales_bill).val(sales_map.bill_no);
        $("[name='billdate']", $edit_sales_bill).val(sales_map.date.split(" ")[0]);
        $("[name='orderno']", $edit_sales_bill).val(sales_map.orderno);
        $("[name='due_date']", $edit_sales_bill).val(sales_map.due_date ? sales_map.due_date.split(" ")[0] : "");
        $("[name='qty']", $edit_sales_bill).val(sales_map.qty);
        $("[name='amt']", $edit_sales_bill).val(sales_map.amt);
        $("[name='brokername']", $edit_sales_bill).val(sales_map.brokername);
        $("[name='broker_amount']", $edit_sales_bill).val(sales_map.broker_amount);
        $("[name='status']", $edit_sales_bill).val(sales_map.status);
        $("[name='payment_type']", $edit_sales_bill).val(sales_map.payment_type);
        $("[name='des']", $edit_sales_bill).val(sales_map.des);
        changePage($sales_report, $edit_sales_bill, $(".goBack", $edit_sales_bill));
    });

    $edit_sales_bill.on("click", ".btn", function() {
        var param = getParamObj($edit_sales_bill);
        param["action"] = "update";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "sales.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $(".goBack", $edit_sales_bill).trigger("click");
            onSalesReportData();
        });
    });
}

function initPurchaseReport() {
    var $purchase_report = $("#purchase_report");
    var $edit_purchase_bill = $("#edit_purchase_bill");
    var $data_list = $("#data_list", $purchase_report);

    $("[name='fromdate'],[name='todate']", $purchase_report).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    }).val(new Date().format("yyyy-MM-dd"));

    $("[name='due_date']", $edit_purchase_bill).glDatePicker({
        dateFormat: "yyyy-MM-dd"
    });

    var onPurchaseReportData = function() {
        var param = getParamObj($("#filter", $purchase_report));
        param["action"] = "getAll";
        $data_list.html("");
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "purchase.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            if (!res.DATA || res.DATA.length == 0) {
                $data_list.html("Record Not Found");
                return;
            }
            var mainTotal = 0;
            $.each(res.DATA, function() {
                var list = $(this)[0];
                var total = list.qty * list.amt;
                var $reportBox = $("<div/>").addClass("reportBox").data("data_map", list);
                var $left = $("<div/>").addClass("left").append('BillNo: ' + list.billno + ' <br><b>' + list.party_name + '</b>');
                var $right = $("<div/>").addClass("right").append('Rs. ' + total);
                var $bottom = $("<div/>").addClass("bottom").append('<h1>' + list.date.split(" ")[0] + '</h1><span class="' + (list.status == "UNPAID" ? "red" : "") + '">' + list.status + '</span>');
                $reportBox.append($left, $right, '<div class="clr"></div>', $bottom, '<div class="clr"></div>');
                $data_list.append($reportBox);
                mainTotal += total;
            });
            $data_list.prepend('<div class="total"><a>Total Purchases: Rs. ' + mainTotal + '</a></div><div class="clr"></div>');
        });
    };
    $purchase_report.on("click", "#filter .btn", onPurchaseReportData);

    $purchase_report.on("click", ".reportBox", function() {
        var data_map = $(this).data("data_map");
        if (!data_map) {
            return;
        }
        $("[name='id']", $edit_purchase_bill).val(data_map.id);
        $("[name='name']", $edit_purchase_bill).val(data_map.name);
        $("[name='party_name']", $edit_purchase_bill).val(data_map.party_name);
        $("[name='billno']", $edit_purchase_bill).val(data_map.billno);
        $("[name='date']", $edit_purchase_bill).val(data_map.date.split(" ")[0]);
        $("[name='due_date']", $edit_purchase_bill).val(data_map.due_date ? data_map.due_date.split(" ")[0] : "");
        $("[name='qty']", $edit_purchase_bill).val(data_map.qty);
        $("[name='amt']", $edit_purchase_bill).val(data_map.amt);
        $("[name='brokername']", $edit_purchase_bill).val(data_map.brokername);
        $("[name='broker_amount']", $edit_purchase_bill).val(data_map.broker_amount);
        $("[name='status']", $edit_purchase_bill).val(data_map.status);
        $("[name='payment_type']", $edit_purchase_bill).val(data_map.payment_type);
        $("[name='des']", $edit_purchase_bill).val(data_map.des);
        changePage($purchase_report, $edit_purchase_bill, $(".goBack", $edit_purchase_bill));
    });

    $edit_purchase_bill.on("click", ".btn", function() {
        var param = getParamObj($edit_purchase_bill);
        param["action"] = "update";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "purchase.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            setMessage(res.MESSAGE, 1);
            $(".goBack", $edit_purchase_bill).trigger("click");
            onPurchaseReportData();
        });
    });
}

function initProfile() {
    var $profile = $("#profile");
    var $change_password = $("#change_password");

    var setProfileData = function(res) {
        if (!res) {
            res = jQuery.parseJSON(localStorage.getItem("user"));
        }
        $("[title=name]", $profile).html(res.DATA.name);
        $("[title=mobile]", $profile).html(res.DATA.mobile);
    };
    setProfileData();
    var getProfileData = function() {
        var param = new Object();
        param["action"] = "getprofile";
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "user.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS != 1) {
                setMessage(res.MESSAGE, 2);
                return;
            }
            localStorage.setItem("user", responseText);
            setProfileData(res);
        });
    };
    getProfileData();

    $profile.on("click", "#chng_psw", function() {
        changePage($profile, $change_password, $(".goBack", $change_password));
    });

    $change_password.on("click", ".btn", function() {
        var param = new getParamObj($change_password);
        param["action"] = "changepassword";
        if (param["oldpassword"].length == 0) {
            setMessage("please enter old password", 2, "", $("[name=oldpassword]", $change_password));
            return;
        }
        if (param["newpassword"].length < 6) {
            setMessage("NewPassword length must be greater than 6 digits.", 2, "", $("[name=newpassword]", $change_password));
            return;
        }
        if (param["newpassword"] != param["retypenewpassword"]) {
            setMessage("The password entered does not match.", 2, "", $("[name=newpassword],[name=retypenewpassword]", $change_password));
            return;
        }
        oLoadingDiv.show(true, true);
        jqueryAjax(APP.API_VERSION + "user.php", param, function(responseText) {
            oLoadingDiv.hide();
            var res = jQuery.parseJSON(responseText);
            if (res.STATUS == 1) {
                setMessage(res.MESSAGE, 1);
                setTimeout(function() {
                    window.location = "profile.html";
                }, 1000);
            } else {
                setMessage(res.MESSAGE, 2);
            }
        });
    });
}

var confirmPopup = function(content, onYesClick, onNoClick) {
    var $blacklayer = $(".a_black_layer.blackbg").last();
    var $conf_pop = $("#confirm_popup");
    $(".get_detail_pop p").html(content);
    $blacklayer.fadeIn(200, function() {
        $conf_pop.css("display", "block");
        $conf_pop.CenterIt();
    });

    $(".b_Yes,.b_No", $conf_pop).unbind("click").click(function() {
        $conf_pop.css("display", "none");
        $blacklayer.fadeOut(200);
    });
    $(".b_Yes", $conf_pop).click(onYesClick);
    $(".b_No", $conf_pop).click(onNoClick);
};

Date.prototype.format = function(format) //author: meizz
{
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1,
                (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                    RegExp.$1.length == 1 ? o[k] :
                    ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

function ObjLength(obj) {
    $.each(obj, function() {

    });
}