public class Plan1571775044723 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {

}

DecreaseTraffic("A");

}


}
}
