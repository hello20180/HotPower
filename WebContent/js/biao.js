// JavaScript Document
        $(document).ready(function(){
            var tableObject = $('#tableSort');//��ȡidΪtableSort��table����
            var tbHead = tableObject.children('thead');//��ȡtable�����µ�thead
            var tbHeadTh = tbHead.find('tr th');//��ȡthead�µ�tr�µ�th
            var tbBody = tableObject.children('tbody');//��ȡtable�����µ�tbody
            var tbBodyTr = tbBody.find('tr');//��ȡtbody�µ�tr
            var sortIndex = -1; //��ʼ������
            tbHeadTh.each(function() {//����thead��tr�µ�th
                var thisIndex = tbHeadTh.index($(this));//��ȡth���ڵ��к�
                //����Ƴ��;۽���Ч��������Ҫ
                $(this).mouseover(function(){ //����ƿ��¼�
                    tbBodyTr.each(function(){//����tbody�µ�tr
                        var tds = $(this).find("td");//��ȡ�к�Ϊ����index��td���󼯺�
                        $(tds[thisIndex]).addClass("hover");
                    });
                }).mouseout(function(){ //���۽�ʱ��
                    tbBodyTr.each(function(){
                        var tds = $(this).find("td");
                        $(tds[thisIndex]).removeClass("hover");
                    });
                });

                $(this).click(function() {  //������¼�
                    var dataType = $(this).attr("type"); //��ȡ��ǰ����е� type
                    checkColumnValue(thisIndex, dataType); //�Ե�ǰ������н������� ����ǰ�������������ͣ�
                });
            });

            //��ʾЧ��������Ҫ
            $("tbody tr").removeClass();//���Ƴ�tbody��tr������css��
            $("tbody tr").mouseover(function(){
                $(this).addClass("hover");
            }).mouseout(function(){
                $(this).removeClass("hover");
            });

            //�Ա������
            function checkColumnValue(index, type) {
                var trsValue = new Array();  //����һ���µ��б�
                tbBodyTr.each(function() { //�������е�tr��ǩ
                    var tds = $(this).find('td');//�������е� td ��ǩ
                    //����ǰ�ĵ���� push ��һ���µ��б���
                    //���� ��ǰ�е� ���͡���ǰ������ ֵ���͵�ǰ�е�ֵ
                    trsValue.push(type + ".separator" + $(tds[index]).html() + ".separator" + $(this).html());
                    $(this).html("");//��յ�ǰ��
                });
                var len = trsValue.length;//��ȡ����Ҫ��Ϸ���еĳ���
                if(index == sortIndex){//sortIndex =-1
                    trsValue.reverse();//???
                } else {
                    for(var i = 0; i < len; i++){//�������е���
                        type = trsValue[i].split(".separator")[0];// ��ȡҪ���������
                        for(var j = i + 1; j < len; j++){
                            value1 = trsValue[i].split(".separator")[1];//��ǰֵ
                            value2 = trsValue[j].split(".separator")[1];//��ǰֵ����һ��
                            if(type == "number"){
                                //js ��Ԫ����  ��� values1 ���� '' ���գ� ��ô����ֵ��Ϊ0������ ��ֵΪ��ǰֵ
                                value1 = value1 == "" ? 0 : value1;
                                value2 = value2 == "" ? 0 : value2;
                                //parseFloat() �����ɽ���һ���ַ�����������һ����������
                                //�ú���ָ���ַ����е��׸��ַ��Ƿ������֡�����ǣ�����ַ������н�����ֱ���������ֵ�ĩ��Ϊֹ��Ȼ�������ַ��ظ����֣���������Ϊ�ַ�����
                                //����ַ����ĵ�һ���ַ����ܱ�ת��Ϊ���֣���ô parseFloat() �᷵�� NaN��
                                if(parseFloat(value1) > parseFloat(value2)){//�����ǰֵ ���� ��һ��ֵ
                                    var temp = trsValue[j];//��ô�ͼ�ס �� ���Ǹ�ֵ
                                    trsValue[j] = trsValue[i];
                                    trsValue[i] = temp;
                                }
                            } else if(type == "ip"){
                                if(ip2int(value1) > ip2int(value2)){
                                    var temp = trsValue[j];
                                    trsValue[j] = trsValue[i];
                                    trsValue[i] = temp;
                                }
                            } else {
                                //JavaScript localeCompare() ���� �ñ����ض���˳�����Ƚ������ַ�����
                                //˵���ȽϽ�������֡�
                                // ��� stringObject С�� target���� localeCompare() ����С�� 0 ������
                                // ��� stringObject ���� target����÷������ش��� 0 ������
                                // ��������ַ�����ȣ�����ݱ����������û�����𣬸÷������� 0��
                                if (value1.localeCompare(value2) > 0) {//�÷��������ݹȸ������
                                    var temp = trsValue[j];
                                    trsValue[j] = trsValue[i];
                                    trsValue[i] = temp;
                                }
                            }
                        }
                    }
                }
                for(var i = 0; i < len; i++){
                    //��������� ֵ ���뵽 �����
                    //:eq(index) ƥ��һ����������ֵ��Ԫ��
                    $("tbody tr:eq(" + i + ")").html(trsValue[i].split(".separator")[2]);
                    //console.log($("tbody tr:eq(" + i + ")").html())
                }
                sortIndex = index;
            }
            //IPת������ ����������
            function ip2int(ip){
                var num = 0;
                ip = ip.split(".");
                //Number() �����Ѷ����ֵת��Ϊ���֡�
                num = Number(ip[0]) * 256 * 256 * 256 + Number(ip[1]) * 256 * 256 + Number(ip[2]) * 256 + Number(ip[3]);
                return num;
            }
            })