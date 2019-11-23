<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        function displayDD(a) {
            var c = document.getElementsByClassName("active");
            for (var i = 0; i < c.length; i++) {
                c[i].style.display = "none";
            }
            document.getElementById(a).style.display = "block";
        }

        function updateTextInput(val) {
            document.getElementById('textInput').value = val;
        }
    </script>
    <script>
        $( function() {
            $( "#slider-range" ).slider({
                range: true,
                min: 0,
                max: 62300000,
                values: [ 0, 62300000 ],
                slide: function( event, ui ) {
                    $( "#amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
                }
            });
            $( "#amount" ).val($( "#slider-range" ).slider( "values", 0 ) +
                " - " + $( "#slider-range" ).slider( "values", 1 ) );
        } );
    </script>
    <script>
        $( function() {
            $( "#slider" ).slider({
                range: true,
                min: 2002,
                max: 2019,
                values: [ 2002, 2019 ],
                slide: function( event, ui ) {
                    $( "#am" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
                }
            });
            $( "#am" ).val($( "#slider" ).slider( "values", 0 ) +
                " - " + $( "#slider" ).slider( "values", 1 ) );
        } );
    </script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="/home" style="all: initial; float: left; left: 20%;"><img src="../images/compass-icon.png"
                                                                       class="image-icon"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
<#--        <form class="form-inline my-2 my-lg-0">-->
<#--            <input class="form-control mr-sm-2" type="search" placeholder="Search model" aria-label="Search">-->
<#--            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="color: white;">Search</button>-->
<#--        </form>-->
    </div>
    <#if user??>
        <div class="modal fade" id="carsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1>Your favourite cars: </h1>
                    </div>
                    <div class="modal-body">
                        <#list favourite_cars as car>
                            <a href="/cars/${car.id}">
                                ${car.maker.makerName} ${car.model.name}
                            </a><br>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#carsModal" style="color: white;">${user.username}</button>
    <#else>
        <!-- Modal -->
        <div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                in</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signIn">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>

                            <div class="input-group mb-3">
                                <input type="checkbox" name="rememberMe" id="Form-remember1">
                                <label data-error="wrong" data-success="right" for="Form-remember1">&nbsp;&nbsp;Remember
                                    me</label>
                            </div>

                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign
                                    in
                                </button>
                            </div>

                            <input name="originPage" value="/home" style="display: none">
                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->


    <#--                SIGN UP FORM-->
        <!-- Modal -->
        <div class="modal fade" id="SignUpForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                up</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signUp">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>
                            <input name="originPage" value="/home" style="display: none">

                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign
                                    up
                                </button>
                            </div>
                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm" style="color: white">Sign
                In</a>
        </div>

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#SignUpForm" style="color: white">Sign Up</a>
        </div>
    </#if>
</nav>
<div id="container">
    <#--    MENU-->
    <form method="post" action="/test">
        <div id="menu">
            <dl>
                <dt style= "outline: 1px solid #000000; background-color: lightgrey"><a href="#">
                        <button id="menuButton" type="button" class="btn btn-link btn-lg btn-block"
                                onclick="displayDD('active-year')" style="background-color: lightgrey; color: black; border: none">
                            Year
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-year" class="active" style="display: none">
                    <p>
                        <label for="amount">Year range:</label>
                        <input type="text" name="year" id="am" readonly style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="slider"></div>
                </dd>
                <#--            Make-->
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-make')" style="background-color: lightgrey; color: black; border: none">
                            Make
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-make" class="active" style="display: none">
                    <#list makers as maker>
                        <div class="form-check" align="left">
                            <label for="${maker.id}">
                                <input type="checkbox" name="maker_id-${maker.id}" class="form-check-input"
                                       id="${maker.id}">${maker.makerName}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <#--Price-->
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-price')" style="background-color: lightgrey; color: black; border: none">Price
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-price" class="active" style="display: none">
                    <p>
                        <label for="amount">Price range:</label>
                        <input type="text" name="avg_price" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="slider-range"></div>
                </dd>
                <#--            Type-->
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-type')" style="background-color: lightgrey; color: black; border: none">
                            Type
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-type" class="active" style="display: none">
                    <#list types as type>
                        <div class="form-check" align="left">
                            <label for="type">
                                <input type="checkbox" class="form-check-input" name="type-'${type}'" id="type">${type}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-engine')" style="background-color: lightgrey; color: black; border: none">Engine
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-engine" class="active" style="display: none">
                    <#list engines as engine>
                        <div class="form-check" align="left">
                            <label for="${engine.id}">
                                <input type="checkbox" class="form-check-input" name="engine-${engine.id}"
                                       id="${engine.id}">${engine.engineType}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-transmission')" style="background-color: lightgrey; color: black; border: none">Transmission
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-transmission" class="active" style="display: none">
                    <#list transmissions as transmission>
                        <div class="form-check" align="left">
                            <label for="${transmission.id}">
                                <input type="checkbox" class="form-check-input" name="transmission-${transmission.id}"
                                       id="${transmission.id}">${transmission.name}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <dt style="background-color: lightgrey; outline: 1px solid #000000;"><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-capacity')" style="background-color: lightgrey; color: black; border: none">Capacity
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-capacity" class="active" style="display: none">
                    <#list capacities as cap>
                        <div class="form-check" align="left">
                            <label for="${cap}">
                                <input type="checkbox" class="form-check-input" name="capacity-${cap}"
                                       id="${cap}">${cap}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
            </dl>
            <button type="submit">Submit</button>
        </div>
    </form>

    <div id="content">
        <#if cars??>
            <div id="top-left" style="float: left; height: 50%; width: 50%"><#if cars[0]??>
                    <a href="/cars/${cars[0].id}">
                        <#--                <img style="width: 80%" height="300px" src="../images/cars/Renault_Duster.png">-->

                        <img style="width: 80%" height="300px" src="../images/cars/${cars[0].imagePath}">

                    </a>
                </#if>
            </div>
            <div id="top-right" style="float: right; height: 50%; width: 50%;"><#if cars[1]??>
                <a href="/cars/${cars[1].id}">
                <#--                <img style="width: 80%" height="300px" src="../images/cars/Kia_Rio.png">-->

                    <img style="width: 80%" height="300px" src="../images/cars/${cars[1].imagePath}">
                    </a></#if>
            </div>
            <div id="bottom-left" style="float: left; height: 50%; width: 50%"><#if cars[2]??>
                <a href="/cars/${cars[2].id}">

                <#--                <img style="width: 80%" height="300px" src="../images/cars/Toyota_Camry.png">-->
                    <img style="width: 80%" height="300px" src="../images/cars/${cars[2].imagePath}">
                    </a></#if>
            </div>
            <div id="bottom-right" style="float: right; height: 50%; width: 50%"><#if cars[3]??>
                    <a href="/cars/${cars[3].id}">
                        <#--                <img style="width: 80%" height="300px" src="../images/cars/Chevrolet_Niva.png">-->
                        <img style="width: 80%" height="300px" src="../images/cars/${cars[3].imagePath}">
                    </a>
                </#if>
            </div>
        <#else>
            <div id="top-left" style="float: left; height: 50%; width: 50%">
                <a href="/cars/12">

                    <img style="width: 80%" height="300px" src="../images/cars/Chevrolet_Niva.png">
                </a>
            </div>

            <div id="top-right" style="float: left; height: 50%; width: 50%">
                <a href="/cars/5">

                    <img style="width: 80%" height="300px" src="../images/cars/Toyota_RAV4.png">
                </a>
            </div>
            <div id="bottom-left" style="float: left; height: 50%; width: 50%">
                <a href="/cars/6">

                    <img style="width: 80%" height="300px" src="../images/cars/Toyota_Camry.png">
                </a>
            </div>
            <div id="bottom-right" style="float: left; height: 50%; width: 50%">
                <a href="/cars/26">
                    <img style="width: 80%" height="300px" src="../images/cars/BMW_M5.png">
                </a>
            </div>
        </#if>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>