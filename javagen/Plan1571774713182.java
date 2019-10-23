public class Plan1571774713182 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
DecreaseDimmer("C");
StartServer("B");

}

DecreaseTraffic("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}




}
}
