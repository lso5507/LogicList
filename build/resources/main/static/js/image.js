  async function imageDelete(arr){
            let formData = new FormData();
            formData.append('imgArr',arr);
            let data = await fetch(location.origin.toString()+"/image-delete", {
                method: 'POST',
                cache: 'no-cache',
                body: formData // body 부분에 폼데이터 변수를 할당
            })
            data=data.json();
            if(data.result=="success"){
                return true;
            }
            return false;
        }

  function imageDeleteTest(arr){
            let formData = new FormData();
            formData.append('imgArr',arr);
            console.log(editor.getMarkdown());
            console.log(location.origin.toString())
            fetch(location.origin.toString()+"/image-delete", {
                method: 'POST',
                cache: 'no-cache',
                body: formData // body 부분에 폼데이터 변수를 할당
            })
            .then((response) => response.json())
            .then((data) => {
               if(data.result=="success"){
               }
               else if(data.result=="failed"){
                alert("실패")
               }
            })
            .then().catch(function(err){
                console.log(err);

            })
        }
        <!-- 본문 이미지 주소와 imgArr비교하여 삭제요청        -->
        function imageFilter(ele,imgArr){
            let result=imgArr;
            let contentStr=contentToArray(ele)
            for(let i=0;i<imgArr.length;i++){
                for(let j=0;j<contentStr.length;j++){
                    if(imgArr[i]===contentStr[j]){
                        result.splice(i,1)
                        console.log("Delete Element::"+imgArr[i]);
                    }
                }
            }

            return result;

        }
        function contentToArray(ele){
             let contentStr=ele.split("![]").filter(str=>str.includes("https://logiclist"));
            for(let j=0;j<contentStr.length;j++){
              contentStr[j]=contentStr[j].substring(contentStr[j].indexOf("(")+1,contentStr[j].indexOf(")"));
            }
            return contentStr;
        }