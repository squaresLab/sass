public class Plan1571769432638 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("C");
}

DecreaseDimmer("A");

}

for (int i = 0; i < 5 ; i++) {
StartServer("C");
}


StartServer("A");


}
}
