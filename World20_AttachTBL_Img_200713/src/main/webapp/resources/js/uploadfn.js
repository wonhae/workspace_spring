	function checkImage(filename) {  /* 이미지파일여부 */  /* filename은 result에 있다.  */
			var idx = filename.lastIndexOf(".");
			
			var format = filename.substring(idx+1).toUpperCase();  /* 그냥 png 를 원한다. */
			
			if(format == 'PNG' || format == 'JPG' || format == 'JPEG' || format == 'GIF') {
				return true;
			} else {
				return false;
			}
		}

		function getOriginalName(filename) {  /* 뒤에서부터 언더바 찾으면 안됨. 여기서도 이미지 파일인지 아닌지 확인하는 작업이 필요하다.  */
			if(checkImage(filename)){ /* 이미지파일의 경우 언더바가 2번째 있을때 . 좀복잡쓰  */
				var idx = filename.indexOf("_");  /* 이게 13이 나온다. 첫번째 _찾아라 */
				idx = filename.indexOf("_", idx+1);  /* 14번째부터 찾아라!! 첫번째 _ 찾아서 더하기 1하고 그이후에 _ 찾아라!!  */
				return filename.substring(idx+1); 
				
				
			} else { /* 이미지파일이 아닐 경우 언더바가 1번째 있을때 */
				var idx = filename.indexOf("_");
				return filename.substring(idx+1);  /* 오리지널네임.확장자 */
			}
		}

		function getImageLink(result) { /* 7.10 이미지파일인지아닌지 구별 . 이미지 파일일 경우만 작업. 아닐경우 그냥 리턴*/
			if(checkImage(result)){
				return result.substring(0,12) + result.substring(14);
				/* var idx = result.indexOf("s_");
				var filename = result.substring(0,idx) */
			} else {
				return result;
			}
		}