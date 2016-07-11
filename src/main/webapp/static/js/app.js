var APP = new Object();
APP.BASEURL = "http://localhost:8080/";
APP.isSupportTransform = true;

function changePage($oldPage, $newPage, $backDiv, isBack, onChange, onBack) {
	var onBackClick = function() {
		oLoadingDiv.hide();
		if (APP.isSupportTransform) {
			$newPage.addClass("pt-page-moveToRight");
			$oldPage.addClass("pt-current-page pt-page-moveFromLeft");
			window.setTimeout(function() {
				$newPage.removeClass("pt-current-page pt-page-moveToRight");
				$oldPage.removeClass("pt-page-moveFromLeft");
				if (onBack) {
					onBack();
				}
			}, 401);
		} else {
			$newPage.animate({
				"margin-left" : windowWidth + "px"
			}, 400, "easeOutExpo");
			$oldPage.css("margin-left", -windowWidth + "px").addClass(
					"pt-current-page").animate({
				"margin-left" : 0 + "px"
			}, 400, "easeOutExpo", function() {
				$newPage.removeClass("pt-current-page");
				if (onBack) {
					onBack();
				}
			});
		}
		if ($backDiv) {
			$backDiv.closest("header").parent().find(".loding_container").css(
					"display", "none");
		}
	};
	if ($backDiv) {
		$backDiv.unbind("click.back").bind("click.back", onBackClick);
	}
	if (isBack) {
		onBackClick();
	} else {
		if (APP.isSupportTransform) {
			$oldPage.addClass("pt-page-moveToLeft");
			$newPage.addClass("pt-current-page pt-page-moveFromRight");
			window.setTimeout(function() {
				$oldPage.removeClass("pt-current-page pt-page-moveToLeft");
				$newPage.removeClass("pt-page-moveFromRight");
				if (onChange) {
					onChange();
				}
			}, 401);
		} else {
			$oldPage.animate({
				"margin-left" : -windowWidth + "px"
			}, 400, "easeOutExpo");
			$newPage.css("margin-left", windowWidth + "px").addClass(
					"pt-current-page").animate({
				"margin-left" : 0 + "px"
			}, 400, "easeOutExpo", function() {
				$oldPage.removeClass("pt-current-page");
				if (onChange) {
					onChange();
				}
			});
		}
	}
	$("html,body").animate({
		scrollTop : "0px"
	}, 400);
}
function initSubmit() {
	var $new_page = $("#new_page");
	$new_page.on("click", "[data-page]", function() {
		var page = $(this).data("page");
		if (!page) {
			return;
		}
		
		console.log("go.1..", page);

		changePage($home_page, $("#" + page + ""));
	});

	var $home_page = $("#home_page");
	$home_page.on("click", "[data-page]", function() {
		var page = $(this).data("page");
		if (!page) {
			return;
		}
		console.log("go...", page);

		changePage($home_page, $("#" + page + ""));
	});
	var $birthfrom = $(".divdata");

	var pram;
	$("#submitdata").on("click", function() {
		pram = JSON.stringify(getParamObj($birthfrom));
		console.log(pram);

		isValidBirthFrom(pram);
		s
		jqueryAjax("data/birthregister", pram, function(res) {
			alert(JSON.stringify(res));
		});
	});

	$("#showdata")
			.on(
					"click",
					function() {
						var data = null;
						var self = this;
						window.location = "view";

						jqueryAjaxGet("data/birthregister/getAll", null,
								function(res) {
									self.data = res;
									console.log(JSON.stringify(res));
								});
						var $birthfromPage = $("#new_page1");
						$birthfromPage.html("");
						$birthfromPage.append("<br/><br/>");
						$birthfromPage
								.append("<div  style='overflow-x:auto;'>");
						$("table tr").addClass("tablefor");
						$birthfromPage
								.append("<table border='5' style='font-size: 17px;padding: 7px;color: #6E747F;font-family: 'HelveticaTwoBQ-Bold';'>");

						$birthfromPage.append("<tr>");
						$birthfromPage.append("<th>Id</th>");
						$birthfromPage.append("<th>FirstName</th>");
						$birthfromPage.append("<th>LastName</th>");
						$birthfromPage.append("<th>Sex</th>");
						$birthfromPage.append("<th>DateOfBirth</th>");
						$birthfromPage.append("<th>NameOfFather</th>");

						$birthfromPage.append("<th>BirthPalce</th>");
						$birthfromPage.append("<th>NameOfMother</th>");
						$birthfromPage.append("<th>ParmanentAddress</th>");
						$birthfromPage.append("<th>RegistrationNo</th>");

						$birthfromPage.append("<th>DateOfRegistration</th>");
						$birthfromPage.append("<th>Ward</th>");
						$birthfromPage.append("<th>BirthMonth</th>");
						$birthfromPage.append("<th>Sun</th>");
						$birthfromPage.append("<th>Zone</th>");
						$birthfromPage.append("</tr>");
						for (var i = 0; i < self.data.length; i++) {
							var listing = self.data[i];
							$birthfromPage.append("<tr>");
							$birthfromPage.append("<td>'" + listing.id + "'");
							$birthfromPage.append("<td>'" + listing.firstname
									+ "'");
							$birthfromPage.append("<td>'" + listing.lastname
									+ "'");
							$birthfromPage.append("<td>'" + listing.sex + "'");
							$birthfromPage.append("<td>'" + listing.dateofbirth
									+ "'");
							$birthfromPage.append("<td>'"
									+ listing.nameoffather + "'");
							$birthfromPage.append("<td>'" + listing.birthpalce
									+ "'");
							$birthfromPage.append("<td>'"
									+ listing.nameofmother + "'");
							$birthfromPage.append("<td>'"
									+ listing.parmanentaddress + "'");
							$birthfromPage.append("<td>'"
									+ listing.registrationno + "'");
							$birthfromPage.append("<td>'"
									+ listing.dateofregistration + "'");
							$birthfromPage.append("<td>'" + listing.ward + "'");
							$birthfromPage.append("<td>'" + listing.birthmonth
									+ "'");
							$birthfromPage.append("<td>'" + listing.sun + "'");
							$birthfromPage.append("<td>'" + listing.zone + "'");
							$birthfromPage.append("</tr>");
						}

						$birthfromPage.append("</table></div>");
					});
	
	
	$("#chnagedpassword").on("click", function() {
		var $setting = $("#setting");
		pram = JSON.stringify(getParamObjChnagePassword($setting));
		alert(pram);
		return;
		console.log(pram);
		jqueryAjax("data/birthregister", pram, function(res) {
			alert(JSON.stringify(res));
		});
	});

}
function initview() {
	jqueryAjaxGet("data/birthregister/getAll", null, function(res) {
		self.data = res;
		console.log(JSON.stringify(res));

	});

	var $birthfromPage = $("#new_page1");
	$birthfromPage.html("");
	$birthfromPage.append("<div  style='overflow-x:auto;'>");
	$("table tr").addClass("tablefor");
	$birthfromPage.append("<table>");

	$birthfromPage.append("<tr>");
	$birthfromPage.append("<th>Id</th>");
	$birthfromPage.append("<th>FirstName</th>");
	$birthfromPage.append("<th>LastName</th>");
	$birthfromPage.append("<th>Sex</th>");
	$birthfromPage.append("<th>DateOfBirth</th>");
	$birthfromPage.append("<th>NameOfFather</th>");

	$birthfromPage.append("<th>BirthPalce</th>");
	$birthfromPage.append("<th>NameOfMother</th>");
	$birthfromPage.append("<th>ParmanentAddress</th>");
	$birthfromPage.append("<th>RegistrationNo</th>");

	$birthfromPage.append("<th>DateOfRegistration</th>");
	$birthfromPage.append("<th>Ward</th>");
	$birthfromPage.append("<th>BirthMonth</th>");
	$birthfromPage.append("<th>Sun</th>");
	$birthfromPage.append("<th>Zone</th>");
	$birthfromPage.append("</tr>");
	for (var i = 0; i < self.data.length; i++) {
		var listing = self.data[i];
		$birthfromPage.append("<tr>");
		$birthfromPage.append("<td>'" + listing.id + "'");
		$birthfromPage.append("<td>'" + listing.firstname + "'");
		$birthfromPage.append("<td>'" + listing.lastname + "'");
		$birthfromPage.append("<td>'" + listing.sex + "'");
		$birthfromPage.append("<td>'" + listing.dateofbirth + "'");
		$birthfromPage.append("<td>'" + listing.nameoffather + "'");
		$birthfromPage.append("<td>'" + listing.birthpalce + "'");
		$birthfromPage.append("<td>'" + listing.nameofmother + "'");
		$birthfromPage.append("<td>'" + listing.parmanentaddress + "'");
		$birthfromPage.append("<td>'" + listing.registrationno + "'");
		$birthfromPage.append("<td>'" + listing.dateofregistration + "'");
		$birthfromPage.append("<td>'" + listing.ward + "'");
		$birthfromPage.append("<td>'" + listing.birthmonth + "'");
		$birthfromPage.append("<td>'" + listing.sun + "'");
		$birthfromPage.append("<td>'" + listing.zone + "'");
		$birthfromPage.append("</tr>");
	}

	$birthfromPage.append("</table></div>");

}
function initRegisation() {
	var self = this;
	var $birthfrom = $(".divdata");
	var response = null;

	$("#username").blur(
			function() {
				var username = $("#username").val();

				jqueryAjaxGet("data/regisation?username=" + username, null,
						function(res) {
							self.response = res;
						});
				if (self.response.username) {
					$("#error").text("User Name is already exist ");
					return;
				}
			});

	$("#regisationData").on("click", function() {
		pram = JSON.stringify(getParamObjRegisation($birthfrom));
		if (self.response.username) {
			$("#error").text("User Name is already exist ");
			return;
		}
		jqueryAjax("data/regisation", pram, function(res) {

			if (res.username) {
				$("#error").text("You are Successfully Regisation");
				self.window.location = "login";
			} else {
				$("#error").text("Regisation faild ...");
			}
		});

	});

}
function isValidBirthFrom(pram) {
	console.log("isValidBirthFrom :: ", pram);

	// if (pram.firstname) {
	// alert("firstname shoeuld notbe null");
	// return;
	// }
	//
	// if (!pram.lastname) {
	// alert("lastname should notbe null");
	// return;
	// }
	// if (!pram.dateofbirth) {
	// alert("dateofbirth should notbe null");
	// return;
	// }
	//
	// if (!pram.nameoffather) {
	// alert("nameoffather should notbe null");
	// return;
	// }
	// if (!pram.nameofmouther) {
	// alert("nameofmouther should notbe null");
	// return;
	// }
	//
	// if (!pram.birthpalce) {
	// alert("birthpalce should notbe null");
	// return;
	// }
	//
	// if (!pram.registrationno) {
	// alert("registrationno should notbe null");
	// return;
	// }
	//
	// if (!pram.dateofregistration) {
	// alert("dateofregistration should notbe null");
	// return;
	// }
	//
	// if (!pram.ward) {
	// alert("ward should notbe null");
	// return;
	// }
	// if (!pram.birthmonth ) {
	// alert("birthmonth should notbe null");
	// return;
	// }
	// if (!pram.sun ) {
	// alert("sun should notbe null");
	// return;
	// }
	// if (pram.zone ) {
	// alert("zone should notbe null");
	// return;
	// }

}

function GetValue() {
	var Contain = "";
	$(".divdata :text").each(function() {
		Contain += $(this).val() + "$";
	});
	console.log(Contain);
}
function createContact() {
	var $birthfrom = $(".divdata");
	// $(".submitdata", $birthfrom).click(onsubmit11);

	var $contact = $(".contact");
	var $all = $(".all");
	var $book = $(".noBottom", $all);
	var $home = $(".home", $book);
	var $dashBoard = $(".dashBoard", $home);
	var $iconbook = $(".iconbook", $dashBoard);

	var $dashBoard = $("dashBoard");
	var $getcontact = $(".getcontact");
	var $checkdata = $(".checkdata");
	var onsubmit11 = function() {
		alert("more information");

	}
	var onsubmit = function() {

		alert("OnSubmit button clicked");
		var pram = getParamObj($contact);

		if (pram.name.length == 0 || pram.name == "") {
			alert("name should notbe null");
			return;
		}
		if (pram.mobileNo.length == 0) {
			alert("mobile number should not be null");
			return;
		}
		if (pram.email.length == 0) {
			alert("email should not be null");
			return;
		}

		if (pram.email.length == 0
				|| !(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
						.test(pram.email))) {
			alert("enter valid email");
			return;
		}

		var prame = JSON.stringify(pram);
		alert(prame);
		jqueryAjax("data/contact", prame, function(res) {
			alert(JSON.stringify(res));
		});

	};
	var onget = function() {
		alert("get all contact");
		jqueryAjaxGet("data/get/contact", null, function(res) {
			alert(JSON.stringify(res));
			var $alldata = $(".alldata");
			$alldata.html("");
			$alldata.append("<div class='divdata'> Name </div>");
			$alldata.append("<div class='divdata'>MobileNo</div></br><div>");

			$.each(res, function() {
				var list = $(this)[0];
				$alldata.append("<div>");
				$alldata.append(list.name);
				$alldata.append(list.email);
				$alldata.append("</div>");

			});
			$alldata.append("</div>");
		});
	};

	var oncheck = function() {
		checked();
	};

	$(".btn", $contact).click(onsubmit);
	$(".submitdata", $birthfrom).click(onsubmit11);
	$(".btnget", $getcontact).click(onget);
	$(".btncheck", $checkdata).click(oncheck);

}
function checked() {
	var favorite = [];
	$.each($("input[name='contact']:checked"), function() {
		favorite.push($(this).val());
	});
	alert("My favourite sports are: " + favorite.join(", "));

}

function getParamObj($container) {
	var param = new Object();
	var firstname = $('[name=firstname]').val();
	var lastname = $('[name=lastname]').val();

	var dateofbirth = $("#dateofbirth").val();
	var nameoffather = $('[name=nameoffather]').val();
	var nameofmother = $('[name=nameofmother]').val();
	var birthpalce = $('[name=birthpalce]').val();
	var parmanentaddress = $('[name=parmanentaddress]').val();
	var registrationno = $('[name=registrationno]').val();
	var dateofregistration = $('[name=dateofregistration]').val();
	var ward = $('[name=ward]').val();
	var birthmonth = $('[name=birthmonth]').val();
	var sun = $('[name=sun]').val();
	var zone = $('[name=zone]').val();

	param["firstname"] = firstname;
	param["lastname"] = lastname;
	param["dateofbirth"] = $("#dateofbirth").val();
	param["nameoffather"] = nameoffather;
	param["nameofmother"] = nameofmother;
	param["birthpalce"] = birthpalce;
	param["parmanentaddress"] = parmanentaddress;
	param["registrationno"] = registrationno;
	param["dateofregistration"] = dateofregistration;
	param["ward"] = ward;
	param["birthmonth"] = birthmonth;
	param["sun"] = sun;
	param["zone"] = zone;
	param["sex"] = $("#gender").val();
	// param["dateofbirth"] ="2014/10/02";
	return param;
}
function getParamObjRegisation($container) {
	var param = new Object();
	var username = $('[name=username]').val();
	var password = $('[name=password]').val();

	param["username"] = username;
	param["password"] = password;
	return param;
}

function getParamObjChnagePassword($container) {
	var param = new Object();
	var oldpassword = $('[name=oldpassword]').val();
	var newpassword = $('[name=newpassword]').val();

	param["oldpassword"] = oldpassword;
	param["newpassword"] = newpassword;
	return param;
}

function jqueryAjax(url, param, callback, errorFn) {

	jQuery.ajax({
		url : APP.BASEURL + url,
		method : "POST",
		contentType : "application/json; charset=utf-8",
		data : param,
		success : callback,
		error : function(xhr) {
			oLoadingDiv.hide();
			alert("error")
			setMessage("something went wrong with API.", 2);
			if (errorFn) {
				errorFn(xhr);
			}
		}

	});
}
function jqueryAjaxGet(url, param, callback, errorFn) {

	jQuery.ajax({
		url : APP.BASEURL + url,
		method : "GET",
		contentType : "application/json; charset=utf-8",
		data : param,
		async : false,
		success : callback,
		error : function(xhr) {
			oLoadingDiv.hide();
			alert("error")
			setMessage("something went wrong with API.", 2);
			if (errorFn) {
				errorFn(xhr);
			}
		}

	});
}
