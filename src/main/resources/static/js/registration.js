function showTenantDropdown() {
    document.getElementById("tenantDropdown").style.display = "block";
    document.getElementById("houseDropdown").style.display = "block";
    document.getElementById("workerDropdown").style.display = "none";
}

function showWorkerDropdown() {
    document.getElementById("workerDropdown").style.display = "block";
    document.getElementById("houseDropdown").style.display = "block";
    document.getElementById("tenantDropdown").style.display = "none";
}

function loadProperties(houseId) {
    var url = '/properties/' + houseId;
    $.get(url, function(data) {
        var select = $('select[name="propertyNumber"]');
        select.find('option').remove();
        select.append('<option value="0">Select property</option>');
        $.each(data, function(index, property) {
            select.append('<option value="' + property.number + '">' + property.number + '</option>');
        });
    });
}