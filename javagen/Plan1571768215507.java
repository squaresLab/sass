public class Plan1571768215507 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
DecreaseDimmer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

DecreaseTraffic("A");

if ( StartServer("C") ) {
StartServer("B");
} else {

}


}



}
}
