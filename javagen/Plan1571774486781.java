public class Plan1571774486781 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("B");
}

}

}


for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
