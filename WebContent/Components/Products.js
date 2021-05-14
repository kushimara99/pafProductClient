$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});




$(document).on("click", "#addproduct", function (event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validateProductForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    var type = ($("#hiddenProductIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "ProductClientAPI",
            type: type,
            data: $("#productform").serialize(),
            dataType: "text",
            complete: function (response, status) {
                onProductSaveComplete(response.responseText, status);
            }
        });
});

function onProductSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "successfull") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divProductGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hiddenProductIDSave").val("");
    $("#productform")[0].reset();
}

$(document).on("click", "#btnUpdate", function (event) {
    $("#hiddenProductIDSave").val($(this).data("id"));
    $("#product_id").val($(this).closest("tr").find('td:eq(1)').text());
    $("#product_code").val($(this).closest("tr").find('td:eq(2)').text());
    $("#product_name").val($(this).closest("tr").find('td:eq(3)').text());
    $("#product_price").val($(this).closest("tr").find('td:eq(4)').text());
    $("#quantity").val($(this).closest("tr").find('td:eq(5)').text());
    $("#researcher_id").val($(this).closest("tr").find('td:eq(6)').text());
    $("#timestamp").val($(this).closest("tr").find('td:eq(7)').text());
});

$(document).on("click", "#btnRemove", function (event) {
    $.ajax(
        {
            url: "ProductClientAPI",
            type: "DELETE",
            data: "ID=" + $(this).data("id"),
            dataType: "text",
            complete: function (response, status) {
                onProductDeleteComplete(response.responseText, status);
            }
        });
});

function onProductDeleteComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "successfull") {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divProductGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}

//CLIENT-MODEL================================================================
function validateProductForm() {
	
    // ID
    if ($("#product_id").val().trim() == "") {
        return "Insert Product ID!!!.";
    }
    
    var tmpPro = $("#product_id").val().trim();
    if (!$.isNumeric(tmpPro)) {
    return "Insert a valid Numeric Product ID !!!";
    }
    
    // CODE
    if ($("#product_code").val().trim() == "") {
        return "Insert Product Code!!!.";
    }
    
    // NAME------------------------------
    if ($("#product_name").val().trim() == "") {
        return "Insert Product Name.";
    }
    
    //PRICE
    if ($("#product_price").val().trim() == "") {
        return "Insert Product Price!!!.";
    }
    
    var tmpPrice = $("#product_price").val().trim();
    if (!$.isNumeric(tmpPrice)) {
    return "Insert a valid Numeric Product Price !!!";
    }
    
    $("#product_price").val(parseFloat(tmpPrice).toFixed(2));
    
   
    //Quantity
    if ($("#quantity").val().trim() == "") {
        return "Insert Quantity!!!.";
    }
    
    var tmpQty = $("#quantity").val().trim();
    if (!$.isNumeric(tmpQty)) {
    return "Insert a valid Numeric Quantity !!!";
    }
    
    //Researcher ID
    if ($("#researcher_id").val().trim() == "") {
        return "Insert Researcher ID!!!.";
    }
    
    var tmpRid = $("#researcher_id").val().trim();
    if (!$.isNumeric(tmpRid)) {
    return "Insert a valid Numeric Researcher ID !!!";
    }
    
    //Date
    if ($("#timestamp").val().trim() == "") {
        return "Insert Correct Date !!!";
    }
    
  
    return true;
}