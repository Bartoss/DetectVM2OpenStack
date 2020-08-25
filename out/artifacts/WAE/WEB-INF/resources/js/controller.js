    $(document).ready(function() {
        $( '.table-dark' ).each(function( i ) {

            var worktable = $(this);
            var num_head_columns = worktable.find('thead tr th').length;
            var rows_to_validate = worktable.find('tbody tr');

            rows_to_validate.each( function (i) {

                var row_columns = $(this).find('td').length;
                for (i = $(this).find('td').length; i < num_head_columns; i++) {
                    $(this).append('<td class="hidden"></td>');
                }
            });
        });
    } );

    $.fn.dataTableExt.afnFiltering.push(
        function(oSettings, aData, iDataIndex) {

            console.log(aData);
            if($('#myCheck').is(':checked')){
                if(aData[4] == 'cloudusr'){
                    return aData;
                }
            }else{
                return aData
            }
        }
    );

    $(function() {
        var my_table = $('#myTable').dataTable( {

        });

        $(':checkbox').click(function() {
            my_table.fnDraw();
        });
    });
