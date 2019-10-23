public class Plan1571772750081 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}



for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
StartServer("C");

}


}
}
