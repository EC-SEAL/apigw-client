$(document).ready(function()
{
//    apSelectorLogic();
//    requestAttributesLogic();
//    sendAttributesLogic();
//    consentAttributesLogic();
//    sendRequestLogic();
    
    sendRequestLogicSEAL();
});

function sendRequestLogicSEAL()
{
//    updateRequestAttributesList();
//    updateSendAttributesList();
    
    $('#send-request-button').click(function()
    {
        //updateConsentAttributesList();
        $('#ap-form').submit();
    });

    $('#send-request-cancel').click(function()
    {
        $('#send-request').hide();
    });
}

function apSelectorLogic()
{
    $('#send-request-button').attr('disabled', 'true');

    $('#ap-selector div').click(function(event)
    {
        $('#ap-selector div').removeClass('onclick');
        $(this).addClass('onclick');
        $('#ap-id').val($(this).attr('index'));
        $('#ap-selected').html($(this).html());
        $('#send-request-button').removeAttr('disabled');
    })
}

function updateRequestAttributesList()
{
    var indexs = new Array();

    $('#attributes-request-selection li input[type=checkbox]').each(function()
    {
        if ($(this).is(':checked'))
        {
            $('#selected-attributes-request li[index=' + $(this).val() + ']').show();
            indexs.push($(this).val());
        }
        else
        {
            $('#selected-attributes-request li[index=' + $(this).val() + ']').hide();
        }
    });

    $('#attr-request-list').val(indexs.join());
}

function showRequestAttributesList()
{
    $('#attributes-request-selection').hide();
    $('#selected-attributes-request').show();
    $('#edit-request-attr').show();
}

function requestAttributesLogic()
{
    $('#attributes-request-selection').hide();
    updateRequestAttributesList();

    $('#edit-request-attr').click(function()
    {
        $('#selected-attributes-request').hide();
        $('#edit-request-attr').hide();
        $('#attributes-request-selection').show();
    });

    $('#update-request-attr').click(function()
    {
        updateRequestAttributesList();
        showRequestAttributesList();
    });

    $('#update-request-attr-cancel').click(function()
    {
        $('#attributes-request-selection').hide();
        showRequestAttributesList();

        $('#selected-attributes-request li').each(function()
        {
            if ($(this).is(':visible'))
            {
                $('#attributes-request-selection li input[value=' + $(this).attr('index') + ']').prop('checked', true);
            }
            else
            {
                $('#attributes-request-selection li input[value=' + $(this).attr('index') + ']').prop('checked', false);
            }
        });
    });
}

function updateSendAttributesList()
{
    var indexs = new Array();

    $('#attributes-send-selection li input[type=checkbox]').each(function()
    {
        if ($(this).is(':checked'))
        {
            $('#selected-attributes-send li[index=' + $(this).val() + ']').show();
            indexs.push($(this).val());
        }
        else
        {
            $('#selected-attributes-send li[index=' + $(this).val() + ']').hide();
        }
    });

    $('#attr-send-list').val(indexs.join());
}

function showSendAttributesList()
{
    $('#attributes-send-selection').hide();
    $('#selected-attributes-send').show();
    $('#edit-send-attr').show();
    $('#send-request .form-buttons input').removeAttr('disabled');
}

function sendAttributesLogic()
{
    $('#attributes-send-selection').hide();
    updateSendAttributesList();

    $('#edit-send-attr').click(function()
    {
        $('#selected-attributes-send').hide();
        $('#edit-send-attr').hide();
        $('#attributes-send-selection').show();
    });

    $('#update-send-attr').click(function()
    {
        updateSendAttributesList();
        showSendAttributesList();
    });

    $('#update-send-attr-cancel').click(function()
    {
        $('#attributes-send-selection').hide();
        showSendAttributesList();

        $('#selected-attributes-send li').each(function()
        {
            if ($(this).is(':visible'))
            {
                $('#attributes-send-selection li input[value=' + $(this).attr('index') + ']').prop('checked', true);
            }
            else
            {
                $('#attributes-send-selection li input[value=' + $(this).attr('index') + ']').prop('checked', false);
            }
        });
    });
}

function updateConsentAttributesList()
{
    var consentList = "";

    $('.update-consent-attr').each(function() {
        var index = $('.update-consent-attr').index($(this));
        var indexes = new Array();
        var consentId = null;

        $($('.attributes-consent-selection')[index]).find('li input[type=checkbox]').each(function()
        {
            if ($(this).is(':checked'))
            {
                indexes.push($(this).val());
                consentId = $(this)[0].name;
            }
        });

        if (consentId != null)
        {
            consentList = consentList + "#" + consentId + ':' + indexes.join();
        }
    });

    if (consentList != '') consentList = consentList.substr(1);
    $('#attr-consent-list').val(consentList);
}

function sendRequestLogic()
{
    updateRequestAttributesList();
    updateSendAttributesList();
    //updateConsentAttributesList();

    $('#send-request-button').click(function()
    {
        updateConsentAttributesList();
        $('#ap-form').submit();
    });

    $('#send-request-cancel').click(function()
    {
        $('#send-request').hide();
    });
}

function consentAttributesLogic()
{
    $('.attributes-consent-selection').hide();

    $('.edit-consent').click(function()
    {
        var index = $('.edit-consent').index($(this));
        $($('.attributes-consent-collected')[index]).hide();
        $($('.attributes-consent-selection')[index]).show();
    });

    $('.update-consent-attr-cancel').click(function()
    {
        var index = $('.update-consent-attr-cancel').index($(this));
        $($('.attributes-consent-selection')[index]).hide();
        $($('.attributes-consent-collected')[index]).show();

        $($('.selected-attributes-consent')[index]).children('li').each(function()
        {
            var li = $($('.attributes-consent-selection')[index]).find('ul li input[value=' + $(this).attr('index') + ']');
            if ($(this).is(':visible'))
            {
                $(li).prop('checked', true);
            }
            else
            {
                $(li).prop('checked', false);
            }
        });
    });

    $('.update-consent-attr').click(function()
    {
        var index = $('.update-consent-attr').index($(this));

        $($('.attributes-consent-selection')[index]).find('li input[type=checkbox]').each(function()
        {
            var li = $($('.selected-attributes-consent')[index]).find('li[index=' + $(this).val() + ']');
            if ($(this).is(':checked'))
            {
                $(li).show();
            }
            else
            {
                $(li).hide();
            }
        });

        $($('.attributes-consent-selection')[index]).hide();
        $($('.attributes-consent-collected')[index]).show();
    });
}