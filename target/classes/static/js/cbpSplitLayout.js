/**
 * cbpSplitLayout.js v1.0.0
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops
 * http://www.codrops.com
 */
var detailData = new Array();

(function() {

	'use strict';

	// http://stackoverflow.com/a/11381730/989439
	function mobilecheck() {
		var check = false;
		(function(a){if(/(android|ipad|playbook|silk|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))check = true})(navigator.userAgent||navigator.vendor||window.opera);
		return check;
	}

	var splitlayout = document.getElementById( 'splitlayout' ),
		leftSide = splitlayout.querySelector( 'div.intro > div.side-left' ),
		rightSide = splitlayout.querySelector( 'div.intro > div.side-right' ),
		pageLeft = splitlayout.querySelector( 'div.page-left' ),
		pageRight = splitlayout.querySelector( 'div.page-right' ),
		eventtype = mobilecheck() ? 'touchstart' : 'click',
		transEndEventNames = {
			'WebkitTransition': 'webkitTransitionEnd',
			'MozTransition': 'transitionend',
			'OTransition': 'oTransitionEnd',
			'msTransition': 'MSTransitionEnd',
			'transition': 'transitionend'
		},
		transEndEventName = transEndEventNames[Modernizr.prefixed( 'transition' )];

	function init() {
		if( mobilecheck() ) {
			classie.add( splitlayout, 'mobile-layout' );
		}
		classie.add( splitlayout, 'reset-layout' );

		leftSide.querySelector( 'img.img1' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameL").innerHTML =  data[0].caseRoleName;
			document.getElementById("detailTitleL").innerHTML =  data[0].caseTitle;
			document.getElementById("detailSchoolL").innerHTML =  data[0].caseGraSchoolMajor;
			document.getElementById("detailExperienceL").innerHTML =  data[0].caseEntrepreneurialJourney;
			document.getElementById("detailPicL").src =  data[0].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-left' );
		} );

		leftSide.querySelector( 'img.img2' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameL").innerHTML =  data[1].caseRoleName;
			document.getElementById("detailTitleL").innerHTML =  data[1].caseTitle;
			document.getElementById("detailSchoolL").innerHTML =  data[1].caseGraSchoolMajor;
			document.getElementById("detailExperienceL").innerHTML =  data[1].caseEntrepreneurialJourney;
			document.getElementById("detailPicL").src =  data[1].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-left' );
		} );

		leftSide.querySelector( 'img.img3' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameL").innerHTML =  data[2].caseRoleName;
			document.getElementById("detailTitleL").innerHTML =  data[2].caseTitle;
			document.getElementById("detailSchoolL").innerHTML =  data[2].caseGraSchoolMajor;
			document.getElementById("detailExperienceL").innerHTML =  data[2].caseEntrepreneurialJourney;
			document.getElementById("detailPicL").src =  data[2].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-left' );
		} );


		rightSide.querySelector( 'img.img4' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameR").innerHTML =  data[3].caseRoleName;
			document.getElementById("detailTitleR").innerHTML =  data[3].caseTitle;
			document.getElementById("detailSchoolR").innerHTML =  data[3].caseGraSchoolMajor;
			document.getElementById("detailExperienceR").innerHTML =  data[3].caseEntrepreneurialJourney;
			document.getElementById("detailPicR").src =  data[3].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-right' );
		} );

		rightSide.querySelector( 'img.img5' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameR").innerHTML =  data[4].caseRoleName;
			document.getElementById("detailTitleR").innerHTML =  data[4].caseTitle;
			document.getElementById("detailSchoolR").innerHTML =  data[4].caseGraSchoolMajor;
			document.getElementById("detailExperienceR").innerHTML =  data[4].caseEntrepreneurialJourney;
			document.getElementById("detailPicR").src =  data[4].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-right' );
		} );

		rightSide.querySelector( 'img.img6' ).addEventListener( eventtype, function( ev ) {
			var data = getData();
			console.log(data);
			document.getElementById("detailNameR").innerHTML =  data[5].caseRoleName;
			document.getElementById("detailTitleR").innerHTML =  data[5].caseTitle;
			document.getElementById("detailSchoolR").innerHTML =  data[5].caseGraSchoolMajor;
			document.getElementById("detailExperienceR").innerHTML =  data[5].caseEntrepreneurialJourney;
			document.getElementById("detailPicR").src =  data[5].casePicUrl;
			reset();
			classie.add( splitlayout, 'open-right' );
		} );

		// back to intro
		// after transition ends:
		var onEndTransFn = function() {
				this.removeEventListener( transEndEventName, onEndTransFn );
				classie.add( splitlayout, 'reset-layout' );
				document.body.scrollTop = document.documentElement.scrollTop = 0;
			},
			backToIntro = function( ev ) {
				ev.preventDefault();
				ev.stopPropagation();
				var dir = classie.has( ev.target, 'back-right' ) ? 'left' : 'right',
					page = dir === 'right' ? pageRight : pageLeft;
				classie.remove( splitlayout, 'open-' + dir );
				classie.add( splitlayout, 'close-' + dir );
				page.addEventListener( transEndEventName, onEndTransFn );
				document.getElementById("tanglihua").innerHTML =  "我变成唐力华了";
			};

		splitlayout.querySelector( 'a.back-left' ).addEventListener( eventtype, backToIntro );
		splitlayout.querySelector( 'a.back-right' ).addEventListener( eventtype, backToIntro );
	}

	function reset() {
		classie.remove( splitlayout, 'close-right' );
		classie.remove( splitlayout, 'close-left' );
		classie.remove( splitlayout, 'reset-layout' );
	}

	init();

})();

function getData() {

	$.ajax({
		url:'/case/caseShowData',
		dataType:'json',
		async: false,
		success:function(data) {
			//填充数据
			var length = data.length;
			if(length>6){
				length=7;
			}
			for(var i=1; i<length+1; i++){
				document.getElementById("roleName" + i.toString()).innerHTML =  data[i-1].caseRoleName;
				document.getElementById('img' + i.toString()).src = data[i-1].casePicUrl;
			}

			// $("#roleName1").val(data[8].caseRoleName);
			// $("#roleTitle1").val(data[8].caseTitle);
			// $("#roleName2").val(data[0].caseRoleName);
			// $('#roleTitle2').val(data[0].caseTitle);
			// console.log(data);
			detailData = data;
		}
		,error: function (data) {
			// console.log(data);
		}
	});
	return detailData;

}