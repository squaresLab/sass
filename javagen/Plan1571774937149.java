public class Plan1571774937149 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {

}


} else {

}


}

}
}
