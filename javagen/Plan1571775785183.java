public class Plan1571775785183 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("B");
}

StartServer("C");

StartServer("B");

DecreaseTraffic("A");

}


}
}
